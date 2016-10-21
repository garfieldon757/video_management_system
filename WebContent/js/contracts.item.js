'use strict';

angular.module('htApp')
  .controller('ConciergeContractsItemCtrl', function($rootScope, $location, $state, Utils, Alerts, ConciergeContracts, conciergePayments, contract) {
    var conciergeContractsItemCtrl = this;

    angular.extend(conciergeContractsItemCtrl, {
      showRejectForm: false,
      submitted: false,
      contract: contract.contract,
      recentMessages: contract.recentMessages,
      paymentData: conciergePayments,

      toggleMessage: function(message) {
        message.active = !message.active;
      },

      messageCarer: function(contract) {
        $state.go('messages.send', {
          back: $location.path(),
          id: contract.carer._id,
          name: Utils.fullName(contract.carer.details.firstname, contract.carer.details.lastname),
          contract: contract._id
        });
      },

      rejectResons: [
        'The service is no longer needed',
        'I\'m unhappy with the service I have received',
        'Change of circumstance'
      ],

      rejectContract: function() {
        conciergeContractsItemCtrl.showRejectForm = true;
      },

      cancelReject: function() {
        conciergeContractsItemCtrl.showRejectForm = false;
      },

      endContract: function(contract) {
        conciergeContractsItemCtrl.submitted = true;

        if (conciergeContractsItemCtrl.rejectParams.reason == '') {
          return;
        }

        conciergeContractsItemCtrl.loadingReject = true;
        conciergeContractsItemCtrl.rejectParams._id = contract._id;

        var method = null;
        if (conciergeContractsItemCtrl.contract.state === 0)
          method = 'reject';
        else if (conciergeContractsItemCtrl.contract.state === 4)
          method = 'dispute';

        ConciergeContracts[method](conciergeContractsItemCtrl.rejectParams).$promise
          .then(function(res) {
            if (res.status == 'OK') {
              conciergeContractsItemCtrl.showRejectForm = false;

              // Update ui to match rejected contract
              // if contract is pending or was accepted goes to rejected by client
              if (conciergeContractsItemCtrl.contract.state == 0 || conciergeContractsItemCtrl.contract.state == 3) {
                conciergeContractsItemCtrl.contract.state = 2;
              }
              // if contract was active goes to disputed by client
              else if (conciergeContractsItemCtrl.contract.state == 4) {
                conciergeContractsItemCtrl.contract.state = 6;
              }

              // update payments
              conciergeContractsItemCtrl.paymentData = {};

              $rootScope.$emit('contractUpdated', conciergeContractsItemCtrl.contract);
            }
          })
          .catch(function(err) {
            console.error(err);
            Alerts.error('Error rejecting request.');
          })
          .finally(function() {
            conciergeContractsItemCtrl.loadingReject = false;
          });
      },

      rejectParams: {
        reason: '',
        comments: '',
        rating: null
      },

      changeReason: function(reason) {
        conciergeContractsItemCtrl.rejectParams.reason = conciergeContractsItemCtrl.rejectParams.reason == reason ? '' : reason;
      }
    });
  });
