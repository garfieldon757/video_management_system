'use strict';

angular.module('htApp')
  .controller('CarerDashboardCtrl',
    function($rootScope, $scope, $q, ConciergeMessages, Alerts, ContractStates, Pagination, ConciergeContracts,
             inboxMessages, sentMessages, pendingContracts, acceptedContracts, $uiViewScroll, $log, $window,
             MessageUtils, Message) {

      var vm = this;
      var ContractGroup = {
        ALL: 0,
        PENDING: 1,
        ACCEPTED: 2,
        valid: function(value) {
          return value == ContractGroup.ALL ||
            value == ContractGroup.PENDING ||
            value == ContractGroup.ACCEPTED;
        }
      };


      // TODO: DS: remove .state('carer-dashboard.reply' section from /Users/dsmiljanic/Projects/HomeTouch/ht-server/public/app.js


      angular.extend(vm, {
        /**
         * used to show the title of message groups (inbox)
         * messageGroupInfo.inbox - map {<sender_id: {name: <sender_name>}
         *
         * used to show the title of message groups and the name in the input placeholder (sent)
         * messageGroupInfo.sent - map {<recipient_id: {name: <recipient_name>, firstName: <recipient_first_name>}
         *
         * model for the text fields
         * messageGroupInfo.messageText - map {<recipient_id: <message_text>}
         *
         * if there no message has been received reply section is not shown
         * messageGroupInfo.receivedMessageFromUser - map {<recipient_id: <is_there_any_message_from_the_client_givent_by_recipient_id>}
         */
        messageGroupInfo: {inbox: {}, sent: {}, messageText: {}, receivedMessageFromUser: {}},
        /**
         * handle click on the 'Send message' button
         * @param recipientId
         */
        sendMessage: function(recipientId) {
          var lastMessage = _.findLast(vm.sent.messages, function(msg) {
            return msg.senderId === recipientId || msg.recipientId === recipientId;
          });
          if(typeof lastMessage !== 'undefined') {
            var promise;
            if(lastMessage.senderId === recipientId) {
              promise = MessageUtils.createReply(lastMessage._id);
            }
            else {
              promise = MessageUtils.createForward(lastMessage._id);
            }
            promise
              .then(function(msg) {
                msg.body += '\n\n' + vm.messageGroupInfo.messageText[recipientId];
                return Message.save({user_id: recipientId}, msg).$promise
              })
              .then(function() {
                $rootScope.$emit('updateSendList', recipientId);
                Alerts.success('Message sent.');
                vm.messageGroupInfo.messageText[recipientId] = '';
              })
              .catch(function(err) {
                Alerts.error(err);
              })
          }
        },
        /**
         * used to calculate the enable state of the 'Send message' button
         * @param receiverId
         * @returns {boolean}
         */
        sendButtonDisabled: function(receiverId) {
          return (vm.messageGroupInfo.messageText[receiverId] || '').trim().length === 0;
        },
        /**
         * sets the message group header data (inbox) - name of the sender
         * @param messages
         */
        setInboxMessageGroups: function(messages) {
          var userId = $rootScope.currentUser._id;
          vm.messageGroupInfo.inbox = {};
          angular.forEach(messages, function(msg) {
            if(!vm.messageGroupInfo.inbox.hasOwnProperty(msg.senderId)) {
              vm.messageGroupInfo.inbox[msg.senderId] = {
                name: msg.senderName,
                firstName: (msg.senderName || '').split(' ')[0]
              }
            }
          });
        },
        /**
         * sets the message group header data (sent) - name of the recipient, first name of the recipient
         * @param messages
         */
        setSentMessageGroups: function(messages) {
          var userId = $rootScope.currentUser._id;
          vm.messageGroupInfo.sent = {};
          angular.forEach(messages, function(msg) {
            var name = '';
            if(msg.senderId === userId) {
              msg.groupId = msg.recipientId;
              msg.msgStatus = 'Sent';
              name = msg.recipientName;
            }
            else {
              msg.groupId = msg.senderId;
              msg.msgStatus = 'Received';
              vm.messageGroupInfo.receivedMessageFromUser[msg.senderId] = true;
              name = msg.senderName;
            }
            if(!vm.messageGroupInfo.sent.hasOwnProperty(msg.groupId)) {
              vm.messageGroupInfo.sent[msg.groupId] = {
                name: name,
                firstName: (name || '').split(' ')[0]
              };
              if(!vm.messageGroupInfo.messageText.hasOwnProperty(msg.groupId)) {
                vm.messageGroupInfo.messageText[msg.groupId] = '';
              }
            }
          });
        },
        /**
         * Update the content of the contract list(s)
         * @param listIndex: enum ContractGroup.ALL/undefined, ContractGroup.ACCEPTED, ContractGroup.PENDING
         */
        updateContractLists: function(listIndex) {
          listIndex = listIndex || ContractGroup.ALL;
          if(!ContractGroup.valid(listIndex)) {
            return $log.error('Invalid parameter listIndex')
          }
          // update the lists
          var pendingContractsQuery = {
            state: [ContractStates.PENDING],
            limit: vm.pendingContracts.contractsPager.itemsPerPage,
            page: vm.pendingContracts.contractsPager.currentPage
          };
          var acceptedContractsQuery = {
            state: [ContractStates.ACCEPTED, ContractStates.ACTIVE],
            limit: vm.acceptedContracts.contractsPager.itemsPerPage,
            page: vm.acceptedContracts.contractsPager.currentPage
          };
          var promises = [];
          if(listIndex !== ContractGroup.ACCEPTED) promises.push(ConciergeContracts.detailedView(pendingContractsQuery).$promise);
          if(listIndex !== ContractGroup.PENDING) promises.push(ConciergeContracts.detailedView(acceptedContractsQuery).$promise);
          $q.all(promises)
            .then(function(responses) {
              if(listIndex === ContractGroup.ALL) {
                // number of pages can be changed if a user accept/reject a contract so we have to update paging
                vm.pendingContracts.items = responses[0].contracts;
                vm.updatePaging(vm.pendingContracts, responses[0]);
                vm.acceptedContracts.items = responses[1].contracts;
                vm.updatePaging(vm.acceptedContracts, responses[1]);
              }
              else if(listIndex === ContractGroup.PENDING) {
                vm.pendingContracts.items = responses[0].contracts;
              }
              else {
                vm.acceptedContracts.items = responses[0].contracts;
              }
            })
            .catch(function(err) {
              $log.error(err);
              Alerts.error('Something went wrong, please call our support team.');
            });
        },
        updatePaging: function(contracts, reponse) {
          contracts.contractsPager = new Pagination(reponse.limit, reponse.itemCount, reponse.page);
        },
        selectedContract: null,
        showRejectForm: false,
        acceptContract: function(contract) {

          if(!$window.confirm('Accept on contract?'))
            return;

          ConciergeContracts.accept(contract).$promise
            .then(function() {
              $rootScope.$emit('contractUpdated', contract);
              Alerts.success('Contract accepted.');
            })
            .catch(function(err) {
              $log.error(err);
              Alerts.error('Something went wrong, please call our support team.');
            });

          //alert(contractId);
          //$rootScope.$emit('contractUpdated', vm.contract);
        },
        rejectContract: function(contract) {
          vm.selectedContract = contract;
          vm.showRejectForm = true;
        },
        pendingContracts: {
          contractsPager: new Pagination(pendingContracts.limit, pendingContracts.itemCount, pendingContracts.page),
          items: pendingContracts.contracts,
          update: function() {
            vm.updateContractLists(ContractGroup.PENDING);
          }
        },
        acceptedContracts: {
          contractsPager: new Pagination(acceptedContracts.limit, acceptedContracts.itemCount, acceptedContracts.page),
          items: acceptedContracts.contracts,
          update: function() {
            vm.updateContractLists(ContractGroup.ACCEPTED);
          }
        },
        inbox: {
          messagesPager: new Pagination(inboxMessages.limit, inboxMessages.itemCount, inboxMessages.page),
          messages: inboxMessages.messages,
          update: function() {
            var query = {
              id: $rootScope.currentUser._id,
              limit: vm.inbox.messagesPager.itemsPerPage,
              page: vm.inbox.messagesPager.currentPage
            };
            return ConciergeMessages.inbox(query).$promise
              .then(function(result) {
                vm.inbox.messagesPager.totalItems = result.itemCount;
                vm.setInboxMessageGroups(result.messages);
                vm.inbox.messages = result.messages;
              })
              .then(function() {
                $uiViewScroll($('#inbox'));
              })
              .catch(function(err) {
                $log.error(err);

                Alerts.error('Error loading data.');
              })
              .finally(function() {
              });
          }
        },
        sent: {
          messagesPager: new Pagination(sentMessages.limit, sentMessages.itemCount, sentMessages.page),
          messages: sentMessages.messages,
          /**
           * initially and during the page change groupId is `undefined`. groupId is set if a message has been sent
           * @param groupId
           * @returns {*}
           */
          update: function(groupId) {
            var query = {
              id: $rootScope.currentUser._id,
              limit: vm.sent.messagesPager.itemsPerPage,
              page: vm.sent.messagesPager.currentPage
            };
            return ConciergeMessages.sent(query).$promise
              .then(function(result) {
                vm.sent.messagesPager.totalItems = result.itemCount;
                vm.setSentMessageGroups(result.messages);
                vm.sent.messages = result.messages;
              })
              .then(function() {
                // TODO: DS: fix $uiViewScroll in order to scroll properly to the message group
                // $uiViewScroll(groupId ? groupId: $('#sent')); $uiViewScroll seems to be broken!!!
                $uiViewScroll($('#sent'));
              })
              .catch(function(err) {
                $log.error(err);

                Alerts.error('Error loading data.');
              })
              .finally(function() {
              });
          }
        }
      });

      // init

      vm.setInboxMessageGroups(inboxMessages.messages);
      vm.setSentMessageGroups(sentMessages.messages);

      // page events

      $rootScope.$on('updateSendList', function(event, params) {
        vm.sent.update(params);
      });

      $rootScope.$on('contractUpdated', function(event, contract) {
        vm.updateContractLists();
      });

    });
