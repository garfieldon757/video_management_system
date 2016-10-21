'use strict';

angular.module('auth', ['ngCookies'])
/**
 * Initialises user authentication state and offers related functions
 */
  .factory('Auth', function($rootScope, $window, $cookieStore, Session) {
    function login(user) {
      if (user != $rootScope.currentUser) {
        if (user) {
          user.pictureTS = Date.now();
          user.roles = user.roles || {};
          user.isAdmin = !!user.roles.admin;
          user.isCarer = !!user.roles.carer;
          user.isClient = !!user.roles.client;
          user.isConsumer = user.isCarer || user.isClient;
          user.isConciergeCarer = !!user.roles.conciergeCarer;
        }

        $rootScope.currentUser = user || null;
        $rootScope.$emit('currentUserChanged', user);
      }
    }

    function isLoggedIn() {
      return $rootScope.currentUser != null;
    }

    login($cookieStore.get('user'));

    return {
      /**
       * Sets current user to specified user or redirects to login page if no user is specified.
       */
      login: function(user) {
        if (user)
          login(user);
        else
          $window.location = '/app/login#/?back=' + encodeURIComponent($window.location.href);
      },
      /**
       * Logs user out and redirects to specified or login page.
       */
      logout: function(back) {
        Session.logout().$promise
          .then(function() {
            if (back === false)
              $window.location.reload();
            else
              $window.location = back || '/app/login';
          });
      },
      /**
       * Redirects to admin app
       */
      admin: function() {
        $window.location = '/app/admin';
      },
      /**
       * Returns whether the current user has any of the allowed roles. The special value 'true' means
       * all roles are allowed.
       */
      isAuthorised: function(allowed) {
        if (!isLoggedIn())
          return false;

        var hasRole = (allowed === true);
        if (!hasRole && typeof allowed === 'object') {
          hasRole = ['carer', 'client', 'conciergeCarer'].some(function(role) {
            return (allowed[role] && $rootScope.currentUser.roles[role]);
          });

          if (!hasRole && allowed['consumer'] && $rootScope.currentUser.isConsumer)
            hasRole = true;
        }

        return hasRole;
      },
      /**
       * Returns whether the user is logged in
       */
      isLoggedIn: isLoggedIn,
      /**
       * Returns whether current user is admin
       */
      isAdmin: function() {
        return isLoggedIn() && $rootScope.currentUser.isAdmin;
      },
      /**
       * Returns whether current user is a carer
       */
      isCarer: function() {
        return isLoggedIn() && $rootScope.currentUser.isCarer;
      },
      /**
       * Returns whether current user is a client (EP)
       */
      isClient: function() {
        return isLoggedIn() && $rootScope.currentUser.isClient;
      },
      /**
       * Returns whether current user is a concierge carer
       */
      isConciergeCarer: function() {
        return isLoggedIn() && $rootScope.currentUser.isConciergeCarer;
      },
      /**
       * Returns the username of the current user
       */
      getUsername: function () {
        return $rootScope.currentUser ? $rootScope.currentUser.username : 'N/A';
      }
    }
  });
