'use strict';

angular
  .module('intercom', ['htConfig'])
  .run(function($rootScope, $window, $timeout, CONFIG) {
    $rootScope.$on('currentUserChanged', function(event, user) {
      update(user);
    });

    $rootScope.$on('$stateChangeSuccess', function() {
      if (!enabled())
        return;

      $timeout(function() {
        $window.Intercom('update');
      }, 0, false);
    });

    if ($rootScope.currentUser)
      update($rootScope.currentUser);

    ////////

    function enabled() {
      return !!($window.Intercom && CONFIG.intercom && CONFIG.intercom.appID);
    }

    function update(user) {
      if (!enabled())
        return;

      if (user) {
        var settings = {
          app_id: CONFIG.intercom.appID,
          user_id: user._id,
          name: user.name,
          created_at: parseInt(user._id.substr(0, 8), 16)
        };

        if (user.username.indexOf('@') > 0)
          settings.email = user.username;
        $window.Intercom('boot', settings);
        $window.Intercom('trackEvent', 'user-logged-in', settings);
      }
      else
        $window.Intercom('shutdown');
    }
  });
