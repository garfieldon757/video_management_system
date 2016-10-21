'use strict';

angular.module('htApp')
  .directive('paymentList', function(DAYS, $filter) {
    var config;

    var stateLabels = {
      'true': [
        {name: 'Pending', icon: null},
        {name: 'Payment Failure', icon: 'red'},
        {name: 'Paid', icon: 'green'}
      ],
      'false': [
        {name: 'Pending', icon: null},
        {name: 'Released', icon: 'green'},
        {name: 'Paid', icon: 'green'},
        {name: 'Frozen', icon: 'red'},
        {name: 'Refunded', icon: 'green'}
      ]
    };

    function preparePaymentData(data) {
      var i, payment, contract;
      for (i in data.payments) {
        payment = data.payments[i];

        if (angular.isString(payment.contract) && data.contracts)
          payment.contract = data.contracts[payment.contract];

        contract = payment.contract;

        var stateLabel = stateLabels[payment.in][payment.state];

        payment.buttons = [];

        if (payment.in) {
          if (payment.state === 0) {
            if (payment.manual || (contract && contract.state === 3)) {
              payment.buttons.push({
                label: 'Pay Now',
                type: 'primary',
                link: '#/concierge/billing/pay/' + payment._id
              });
            }
            else if (contract && contract.state >= 4 && contract.state <= 6) {
              payment.buttons.push({
                label: 'Cancel Renewal',
                type: 'default',
                link: '#/concierge/billing/cancel/' + payment._id
              });
            }
          }
        }
        else {
          // only display state change date for paid payments (we want to display the due date for the other states - no we don't. Stop being such a fucking halfwit.)
          if (payment.state !== 2)
            payment.date = undefined;

          // pending carer payments
          if (payment.state === 0 && contract) {
            if (contract.state === 5 || contract.state === 6) // contract disputed: show payment as frozen
              stateLabel = stateLabels[payment.in][3];
            else if (contract.state === 7)                    // contract terminated: show payment as refunded
              stateLabel = stateLabels[payment.in][4];
          }
        }

        payment.label = stateLabel.name;
        payment.icon = stateLabel.icon;
      }

      return data;
    }

    function date(d) {
      return $filter('date')(d, config.mediumDayFormat);
    }

    var linker = function(scope, element, attrs) {
      scope.days = DAYS;
      config = scope.config;

      scope.$watch('data', function(newValue, oldValue) {
        if (newValue) {
          scope.data = preparePaymentData(scope.data);
        }
      }, true);
    };

    return {
      restrict: 'A',
      scope: {
        data: '=paymentList',
        config: '=config'
      },
      templateUrl: '/public/app/concierge/billing/payment-list.html',
      link: linker
    };
  });
