'use strict';

angular.module('alerts', [])
  .factory('Alerts', function($rootScope, $timeout) {
    $rootScope.alerts = [];

    function close(index) {
      if (index >= 0 && index < $rootScope.alerts.length)
        $rootScope.alerts.splice(index, 1);
    }

    function show(type, msg) {
      var message = {
        type: type,
        msg: msg
      };

      $rootScope.alerts.push(message);

      $timeout(function() {
        close($rootScope.alerts.indexOf(message));
      }, 4000);
    }

    return {
      close: close,
      success: function(msg) {
        show('success', msg);
      },
      info: function(msg) {
        show('info', msg);
      },
      warning: function(msg) {
        show('warning', msg);
      },
      error: function(msg) {
        if (msg.status) {
          switch (msg.status) {
            case 400:
              msg = 'Invalid data.';
              break;

            case 403:
              msg = 'Operation not permitted.';
              break;

            case 404:
              msg = 'Not found.';
              break;

            default:
              msg = 'Operation failed.';
              break;
          }
        }

        show('danger', msg);
      }
    };
  });
