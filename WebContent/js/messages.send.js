'use strict';

angular.module('htApp')
  .controller('MessagesSendCtrl', function($rootScope, $scope, $state, $stateParams, $location, Utils, Message, Alerts, contact, message) {
    var messagesSendCtrl = this;
    var backLink = false;

    angular.extend(messagesSendCtrl, {
      model: message,
      busy: false,
      contact: contact,
      submitted: false,
      errors: {},

      recipientSelected: function(item) {
        var contact = null;

        if (item) {
          var id = item._id || item;

          messagesSendCtrl.contact.some(function(c) {
            if (c._id == id) {
              contact = c;
              return true;
            }

            return false;
          });

          if (!contact && item._id && item.name) {
            contact = item;
            contact._tmp = true;
            messagesSendCtrl.contact.push(contact);
          }
        }

        if (contact) {
          messagesSendCtrl.model.recipientId = contact._id;
          messagesSendCtrl.model.recipientName = contact.name;
        }
        else {
          messagesSendCtrl.model.recipientId = null;
          messagesSendCtrl.model.recipientName = '';
        }
      },

      closeForm: function() {
        if ($location.search() && $location.search().back) {
          var back = backLink || decodeURIComponent($location.search().back);

          $location
            .search('back', null)
            .search('id', null)
            .search('name', null)
            .search('contract', null)
            .path(back);
        }
        else
          $state.go('messages');
      },

      send: function(form) {
        messagesSendCtrl.submitted = true;

        if (form.$valid) {
          if (!messagesSendCtrl.model.recipientId)
            return Alerts.error('Failed to find recipient');

          messagesSendCtrl.busy = true;

          Message.save({user_id: messagesSendCtrl.model.recipientId}, messagesSendCtrl.model).$promise
            .then(function(data) {
              if (!$scope.messagesCtrl.inbox && $scope.messagesCtrl.messages) {
                data.username = data.recipientName;
                data.userId = data.recipientId;
                $scope.messagesCtrl.messages.unshift(data);
                $scope.messagesCtrl.show(data);
              }

              Alerts.success('Message sent');
              messagesSendCtrl.closeForm();
            })
            .catch(function(err) {
              if (err.status == 400) {
                for (var prop in err.data) {
                  if (err.data[prop].message) {
                    var msg = err.data[prop].message;
                    if (msg) {
                      form[prop].$setValidity('remote', false);
                      messagesSendCtrl.errors[prop] = msg;
                    }
                  }
                }
              }
              else {
                var message = 'Operation failed.';

                if (err.data && err.data.message)
                  message = err.data.message;
                else if (err.status == 403)
                  message = 'Operation not permitted.';

                Alerts.error(message);
              }
            })
            .finally(function() {
              messagesSendCtrl.busy = false;
            });
        }
      }
    });

    function checkForRecipient() {
      var search = $location.search();

      if (search.id && search.name) {
        messagesSendCtrl.model.senderId = $rootScope.currentUser._id;
        messagesSendCtrl.model.senderName = $rootScope.currentUser.name;

        if (search.contract)
          messagesSendCtrl.model.contract = search.contract;

        if (search.back)
          backLink = decodeURIComponent(search.back);

        if (search.contract || search.concierge)
          messagesSendCtrl.concierge = true;

        messagesSendCtrl.recipientSelected({_id: search.id, name: search.name});

        return true;
      }

      return false;
    }

    var addClient = !$rootScope.currentUser.isClient;

    messagesSendCtrl.contact.forEach(function(contact) {
      contact.name = Utils.fullName(contact.details.firstname, contact.details.lastname);
      if (contact._id == $rootScope.currentClient._id)
        addClient = false;
    });

    if (addClient)
      messagesSendCtrl.contact.push($rootScope.currentClient);

    if (checkForRecipient()) {
      messagesSendCtrl.toInputReadonly = true;
    }
    else {
      messagesSendCtrl.model.senderId = $rootScope.currentUser._id;
      messagesSendCtrl.model.senderName = $rootScope.currentUser.name;

      if ($stateParams.user_id)
        messagesSendCtrl.recipientSelected($stateParams.user_id);
    }
  });

