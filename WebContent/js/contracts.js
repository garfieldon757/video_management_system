'use strict';

angular.module('htApp')
  .controller('ConciergeContractsCtrl', function($rootScope, $scope, DAYS, contracts, Account) {
    var conciergeContractsCtrl = this;

    angular.extend(conciergeContractsCtrl, {
      days: DAYS,
      contracts: contracts
    });

    $scope.activeAccount = hasActiveAccount();

    // Account
    function hasActiveAccount() {
      Account.getAccountByUserId.get({
        user_id: $rootScope.currentUser._id
      }).$promise
      .then(function(account) {
        $scope.activeAccount = account.status.name == 'ACTIVE' ? true : false;
      })
      .catch(function(error) {
        $scope.activeAccount = false;
      });
    }

    var cleanUp = $rootScope.$on('contractUpdated', function(event, contract) {
      if (contract && contract._id) {
        for (var i = 0; i < contracts.length; i++) {
          var c = contracts[i];
          if (c && c._id == contract._id) {
            contracts[i] = contract;
            break;
          }
        }
      }
    });

    $scope.$on('$destroy', function() {
      cleanUp();
    })
  });
