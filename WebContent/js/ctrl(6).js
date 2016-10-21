'use strict';

angular.module('htApp')
  .controller('BillingCtrl', function($scope, $rootScope, $q, Alerts, ConciergePayments) {

    $scope.getPayments = function(year) {
      $scope.loading = true;

      year = year || new Date().getFullYear();

      ConciergePayments.get({
        from: year + '-01-01',
        to: year + '-12-31',
        type: 'in'
      }).$promise
        .then(function(data) {

          $scope.data = data;

          $scope.year = year;
          $scope.nextYear = year + 1;
          $scope.previousYear = year - 1;

        })
        .catch(function(err) {
          console.error(err);
          Alerts.error('Error loading data.');
        })
        .finally(function() {
          $scope.loading = false;
        });
    };

    $scope.getPayments();

  });
