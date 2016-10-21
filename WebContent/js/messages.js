'use strict';

angular.module('htApp')
  .controller('MessagesCtrl', function($rootScope, $uiViewScroll, Pagination, Message, Alerts) {
    var messagesCtrl = this;
    var query = {user_id: $rootScope.currentUser._id, all: 1};

    angular.extend(messagesCtrl, {
      inbox: true,
      error: false,
      pager: new Pagination(5),

      remove: function(msg) {
        if (confirm('Remove message \'' + msg.subject + '\'?')) {
          Message.remove({user_id: $rootScope.currentUser._id, message_id: msg._id}).$promise
            .then(function() {
              Alerts.success('Message removed');
              messagesCtrl.messages.splice(messagesCtrl.messages.indexOf(msg), 1);

              if (messagesCtrl.messages.length)
                messagesCtrl.show(messagesCtrl.messages[0]);
            })
            .catch(function() {
              Alerts.error('failed to remove messages');
            })
        }
      },

      show: function(msg) {
        msg.voiceSrc = '/api/users/' + $rootScope.currentUser._id + '/messages/' + msg._id + '/voice';
        messagesCtrl.msg = msg;
        messagesCtrl.selectedItem = messagesCtrl.msg._id;

        if (msg.recipientId === $rootScope.currentUser._id && !msg.read) {
          Message.update({user_id: $rootScope.currentUser._id, message_id: msg._id}, {read: true}).$promise
            .then(function() {
              msg.read = true;
            })
            .catch(function() {
              Alerts.error('Failed to mark message as read');
            });
        }
      },

      showOutbox: function() {
        messagesCtrl.inbox = false;
        messagesCtrl.msg = null;
        messagesCtrl.selectedItem = null;
        messagesCtrl._state = 'loading';

        Message.outbox(query).$promise
          .then(function(data) {
            messagesCtrl.messages = data.messages;
            messagesCtrl.messages.forEach(function(msg) {
              msg.senderId = $rootScope.currentUser._id;
              msg.senderName = $rootScope.currentUser.name;
              msg.userId = msg.recipientId;
              msg.username = msg.recipientName;
            });

            if (data.messages.length)
              messagesCtrl.show(messagesCtrl.messages[0]);

            messagesCtrl.pager.totalItems = data.messages.length;
            messagesCtrl.pager.currentPage = 1;

            messagesCtrl._state = 'list';
          })
          .then(scrollMessageList)
          .catch(function() {
            Alerts.error('Failed to fetch data');
            messagesCtrl.error = true;
            messagesCtrl.errorMessage = 'Failed to load messages.';
          });
      },

      showInbox: function() {
        messagesCtrl.inbox = true;
        messagesCtrl.msg = null;
        messagesCtrl.selectedItem = null;
        messagesCtrl._state = 'loading';

        Message.inbox(query).$promise
          .then(function(data) {
            messagesCtrl.messages = data.messages;
            messagesCtrl.messages.forEach(function(msg) {
              msg.recipientId = $rootScope.currentUser._id;
              msg.recipientName = $rootScope.currentUser.name;
              msg.userId = msg.senderId;
              msg.username = msg.senderName;
            });

            if (data.messages.length)
              messagesCtrl.show(messagesCtrl.messages[0]);

            messagesCtrl.pager.totalItems = data.messages.length;
            messagesCtrl.pager.currentPage = 1;

            messagesCtrl._state = 'list';
          })
          .then(scrollMessageList)
          .catch(function() {
            Alerts.error('Failed to fetch data');
            messagesCtrl.error = true;
            messagesCtrl.errorMessage = 'Failed to load messages.';
          });
      },

      listUpdated: scrollMessageList
    });

    function scrollMessageList() {
      $uiViewScroll($('#message-list'));
    }

    messagesCtrl.showInbox();
  });
