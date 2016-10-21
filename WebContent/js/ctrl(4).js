'use strict';

angular.module('htApp')
  .controller('ContactsCtrl', function($rootScope, $scope, $location, $state, CurrentClient, Contact, Alerts) {
    var currentClient = CurrentClient.get();
    var query = {user_id: currentClient._id};
    $scope.model = {};
    $scope.errors = {};
    $scope.submitted = false;

    $scope.save = function(form) {
      $scope.submitted = true;
      if (form.$valid) {
        Contact.invite(query, $scope.model).$promise
          .then(function() {
            Alerts.success('Invitation sent successfully');
            $scope.closeForm();
          })
          .catch(function(err) {
            if (err.status == 400) {
              for (var prop in err.data) {
                if (err.data[prop].message) {
                  var msg = err.data[prop].message;
                  if (msg) {
                    form[prop].$setValidity('remote', false);
                    $scope.errors[prop] = msg;
                  }
                }
              }
            }
            else
              Alerts.error('Operation failed.');
          });
      }
    };

    $scope.closeForm = function() {
      if ($location.search() && $location.search().back) {
        var back = $location.search().back;
        $location.search('back', null);
        $location.path(back);
      }
      else
        $state.go('home');
    };
  });