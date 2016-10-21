'use strict';

angular.module('htApp')
  .controller('PaymentCtrl', function($scope, $stateParams, $sanitize, $window, Alerts, ConciergePayments) {
    var paymentId = $stateParams.id;

    var info = $scope.info = {
      viewState: 'form',
      processing: false,
      acceptTerms: false,
      months: [],
      years: []
    };

    $scope.paid = false;
    $scope.cancelled = false;
    $scope.reason = '';

    $scope.model = {
      number: '',
      cvc: '',
      exp_month: '',
      exp_year: ''
    };

    for (var m = 1; m <= 12; m++) {
      info.months.push(m < 10 ? '0' + m : m.toString());
    }

    var year = new Date().getFullYear();
    for (var y = year; y < year + 21; y++) {
      info.years.push(y.toString());
    }

    $scope.validate = function(form) {
      form.number.$setValidity('valid', !$scope.model.number || Stripe.card.validateCardNumber($scope.model.number));
      form.cvc.$setValidity('valid', !$scope.model.cvc || Stripe.card.validateCVC($scope.model.cvc));
      form.acceptTerms.$setValidity('required', info.acceptTerms);
    };

    $scope.goBack = function() {
      $window.history.back();
    };

    ConciergePayments.get({
      id: paymentId
    }).$promise
      .then(function(data) {
        $scope.payment = data;
      })
      .catch(function(err) {
        console.error(err);
        Alerts.error('Error loading data.');
      })
      .finally(function() {
        $scope.loading = false;
      });

    $scope.cancel = function(form) {
      if (info.processing) {
        return;
      }

      info.submitted = true;
      info.errorMessage = '';

      if (form.$valid) {
        info.processing = true;

        ConciergePayments
          .cancel({
            _id: paymentId,
            reason: $scope.reason
          }).$promise
          .then(function(data) {
            $scope.cancelled = true;
          })
          .catch(function(error) {
            info.errorMessage = $sanitize(error.data.message) || 'Operation failed. Please contact us if the error persists.';
          })
          .finally(function() {
            info.processing = false;
          });
      }
    };

    $scope.save = function(form) {
      if (info.processing) {
        return;
      }

      info.submitted = true;
      info.errorMessage = '';

      form.acceptTerms.$setValidity('required', info.acceptTerms);

      if (form.$valid) {
        info.processing = true;

        Stripe.card.createToken($scope.model, function(status, response) {
          if (response.error) {
            info.errorMessage = response.error.message;
            info.processing = false;
            $scope.$apply();
          }
          else {
            ConciergePayments
              .charge({
                stripeToken: response.id,
                _id: paymentId,
                name: $scope.model.name,
                email: $scope.model.email
              }).$promise
              .then(function(data) {
                $scope.paid = true;
              })
              .catch(function(error) {
                info.errorMessage = $sanitize(error.data.message) || 'Operation failed. Please contact us if the error persists.';
              })
              .finally(function() {
                info.processing = false;
              });
          }
        });
      }
    };

  });
