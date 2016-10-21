'use strict';

angular.module('utils', [])
  .factory('Utils', function() {
    var YOUTUBE = /(youtu\.be\/|youtube\.com\/watch\?.*v=)([^\s?&=]+)/i;
    var VIMEO = /vimeo\.com/i;

    return {
      contractRepeatOptions: [
        {value: 1, label: 'One week'},
        {value: 2, label: 'Two weeks'},
        {value: -1, label: 'Select end date'},
        {value: 0, label: 'Ongoing - bills weekly'}
      ],
      shuffle: function shuffle(array) {
        var currentIndex = array.length, temporaryValue, randomIndex;

        // While there remain elements to shuffle...
        while (0 !== currentIndex) {
          // Pick a remaining element...
          randomIndex = Math.floor(Math.random() * currentIndex);
          currentIndex -= 1;

          // And swap it with the current element.
          temporaryValue = array[currentIndex];
          array[currentIndex] = array[randomIndex];
          array[randomIndex] = temporaryValue;
        }

        return array;
      },
      fullName: function(firstName, lastName) {
        return ((firstName || '').trim() + ' ' + (lastName || '').trim()).trim();
      },
      videoEmbedURL: function(videoURL) {
        var id = null;
        var url = videoURL;

        if (videoURL) {
          if (YOUTUBE.test(videoURL)) {
            id = YOUTUBE.exec(videoURL)[2];
            url = 'https://www.youtube.com/embed/' + id;
          }
          else if (VIMEO.test(videoURL)) {
            id = videoURL.split('/').pop();

            if (id)
              url = 'https://player.vimeo.com/video/' + id;
          }
        }

        return url;
      }
    };
  })
  .factory('MessageUtils', function($rootScope, $q, $filter, Message) {
    function prefix(pre, str) {
      if (!pre || str.substr(0, pre.length).toLowerCase() == pre.toLowerCase())
        return str;

      return pre + ' ' + str;
    }

    function quote(message) {
      var lines = message.split(/\r?\n/);
      var quoted = '';

      lines.forEach(function(line) {
        quoted += '> ' + line + '\n';
      });

      return quoted;
    }

    return {
      createReply: function makeReply(id) {
        var d = $q.defer();

        if (id) {
          Message.get({user_id: $rootScope.currentUser._id, message_id: id}).$promise
            .then(function(msg) {
              var subject = prefix('Re:', msg.subject);
              var body = '\n\nOn ' + $filter('date')(msg.date, 'medium') + ', ' + msg.senderName + ' wrote:\n' + quote(msg.body);

              d.resolve({
                senderId: $rootScope.currentUser._id,
                senderName: $rootScope.currentUser.name,
                recipientId: msg.senderId,
                recipientName: msg.senderName,
                subject: subject,
                body: body,
                contract: msg.contract ? msg.contract : ''
              });
            })
            .catch(function(err) {
              d.reject(err);
            });
        }
        else
          d.reject(new Error('Message id missing.'));

        return d.promise;
      },
      createForward: function makeForward(id) {
        var d = $q.defer();

        if (id) {
          Message.get({user_id: $rootScope.currentUser._id, message_id: id}).$promise
            .then(function(msg) {
              var subject = prefix('Fwd:', msg.subject);
              var body = '\n\n---------- Forwarded message ----------\n' +
                         'From: ' + (msg.senderName || $rootScope.currentUser.name) + '\n' +
                         'Date: ' + $filter('date')(msg.date, 'medium') + '\n' +
                         'Subject: ' + msg.subject + '\n\n' +
                         msg.body;

              d.resolve({
                senderId: $rootScope.currentUser._id,
                senderName: $rootScope.currentUser.name,
                subject: subject,
                body: body
              });
            })
            .catch(function(err) {
              d.reject(err);
            });
        }
        else
          d.reject(new Error('Message id missing.'));

        return d.promise;
      }
    };
  });
