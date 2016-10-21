'use strict';

angular.module('htApp')
  .factory('CurrentClient', function($rootScope) {
    $rootScope.currentClient = null;
    setForUser($rootScope.currentUser);

    $rootScope.$on('currentUserChanged', function(event, user) {
      setForUser(user);
    });

    function setForUser(user) {
      if (!user) {
        set(null);
        return;
      }

      if (user.isClient)
        set(user);
      else if (user.isCarer) {
        var client = null;
        if (user.contacts) {
          user.contacts.some(function(contact) {
            if (contact.type === 1 || contact.type === 2) {
              client = contact;
              client._id = client.user;
              return true;
            }
            return false;
          });
        }

        set(client);
      }
      else
        set(null);
    }

    function set(client) {
      if (client != $rootScope.currentClient) {
        $rootScope.currentClient = client;
        $rootScope.$emit('currentClientChanged', client);
      }
    }

    return {
      set: set,
      get: function() {
        return $rootScope.currentClient;
      },
      isSet: function() {
        return !!$rootScope.currentClient;
      }
    };
  });
