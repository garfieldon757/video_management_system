'use strict';

angular.module('htApp')
  .controller('InviteCtrl', function($scope, $location, $stateParams, Alerts, Auth, Invite) {
    $scope.invite = {};

    $scope.info = {
      viewState: 'loading',
      errorMessage: '',
      contactType: 0
    };

    $scope.model = {
      password: '',
      details: {
        firstname: '',
        lastname: '',
        email: '',
        mobile: '',
        address: '',
        position: ''
      }
    };

    $scope.logout = function() {
      Auth.logout(false);
    };

    $scope.accept = function(form) {
      $scope.submitted = true;

      if (form.$valid) {
        Invite.accept($stateParams, $scope.model).$promise
          .then(function(user) {
            Auth.login(user);
            $location.path('/');
            Alerts.success('Welcome to HomeTouch! You have been automatically logged in to your new account.');
          })
          .catch(function(err) {
            if (err.status == 400) {
              for (var prop in err.data) {
                if (prop == 'username') {
                  $scope.info.errorMessage = 'A user with this email already exists. If you have already accepted this invitation, please login to your account on the <a href="/applogin">Login Page</a>.';
                  $scope.info.viewState = 'error';
                  return;
                }
                var msg = err.data[prop].message;
                if (msg) {
                  prop = prop.replace(/\./g, '_');
                  if (form[prop]) {
                    form[prop].$setValidity('remote', false);
                    $scope.errors[prop] = msg;
                  }
                }
              }
            }
            else
              Alerts.error(err);
          });
      }
    };

    if ($scope.currentUser) {
      $scope.info.viewState = 'logout';
    }
    else {
      Invite.get($stateParams).$promise
        .then(function(invite) {
          $scope.invite = invite;
          $scope.model.details.firstname = invite.firstname;
          $scope.model.details.lastname = invite.lastname;
          $scope.model.details.email = invite.email;
          $scope.model.details.mobile = invite.mobile;
          $scope.model.details.position = invite.position;
          $scope.info.viewState = 'accept';
        })
        .catch(function(err) {
          $scope.info.error = true;
          $scope.info.errorMessage = err.status == 404 ? 'Invalid invite identifier.' : 'Failed to load invite information.';
          $scope.info.viewState = 'error';
        });
    }
  });
