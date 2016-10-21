'use strict';

angular.module('htApp')
  .controller('NavCtrl', function($scope, $rootScope, $state, $location, CurrentClient, Contact, Alerts) {
    $scope.sendMessageToContact = function(id) {
      if (id)
        $state.go('messages.send', {user_id: id, back: $location.path()});
    };

    $scope.invite = function() {
      $location.search('back', $location.path()).path('/contacts/invite');
    };

    $scope.hasName = function(value) {
      return value && value.details && (value.details.firstname || value.details.lastname);
    };

    Contact.query({user_id: $rootScope.currentUser._id}).$promise
      .then(function(data) {
        $scope.contacts = data;
      })
      .catch(function() {
        Alerts.error('failed to fetch data');
      });
  });