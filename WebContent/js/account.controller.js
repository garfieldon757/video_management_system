'use strict';

angular.module('htApp')
    .controller('AccountController', function($rootScope, $scope, $stateParams, $sanitize, $window, Account) {
      var info = $scope.info = {
        processing: false,
        acceptTerms: false,
        months: [],
        years: []
      };

      $scope.model = {
        number: '',
        cvc: '',
        exp_month: '',
        exp_year: ''
      }

      $scope.accountSetup = false;

      // Setup month and year data
      for (var m = 1; m <= 12; m++) {
        info.months.push(m < 10 ? '0' + m : m.toString());
      }

      var year = new Date().getFullYear();
      for (var y = year; y < year + 21; y++) {
        info.years.push(y.toString());
      }

      // Surely too much shit is going on in this controller?
      $scope.goBack = function() {
        $window.history.back();
      };

      $scope.validate = function(form) {
        form.number.$setValidity('valid', !$scope.model.number ||         Stripe.card.validateCardNumber($scope.model.number));
        form.cvc.$setValidity('valid', !$scope.model.cvc ||       Stripe.card.validateCVC($scope.model.cvc));
      };

      $scope.customerAccount = getCustomerAccount();

      function getCustomerAccount() {

        Account.getAccountByUserId.get({
          user_id: $rootScope.currentUser._id
        }).$promise
        .then(function(account) {
          $scope.customerAccount = account;
        })
      }

      $scope.submitCardSource = function(form) {
        if (info.processing) {
          return;
        }

        info.submitted = true;
        info.errorMessage = '';

        // form.acceptTerms.$setValidity('required', info.acceptTerms);

        if (form.$valid) {
          info.processing = true;

          // Do the stripe thing
          Stripe.card.createToken($scope.model, function(status, response) {
            if (response.error) {
              info.errorMessage = response.error.message;
              info.processing = false;
              $scope.$apply();
            }
            else {
              // Should do a put if account exists - as in add a new card
              Account.createAccountWithStripeToken.save(
              {user_id: $rootScope.currentUser._id},
              {
                user_email: $scope.model.email,
                source: response.id
              }).$promise
              .then(function(shizzle) {
                $scope.accountSetup = true;
              })
              .catch(function(err) {
                info.errorMessage = $sanitize(err.message) || 'Operation failed. Please contact us if the error persists.';
              })
              .finally(function() {
                info.processing = false;
              })
            }
          })
        }
      }
    })
