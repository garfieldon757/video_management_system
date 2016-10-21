'use strict';

angular.module('htApp')
  .directive('contractState', function() {
    var contractStates,
      linker;

    contractStates = [
      {
        text: 'Awaiting Confirmation by Carer',
        cssClass: 'info'
      },
      {
        text: 'Invitation Rejected',
        cssClass: 'danger'
      },
      {
        text: 'Rejected by Customer',
        cssClass: 'danger'
      },
      {
        text: 'Contract Accepted',
        cssClass: 'success'
      },
      {
        text: 'Contract Active',
        cssClass: 'success'
      },
      {
        text: 'Contract Disputed (Termination Asked by Carer)',
        cssClass: 'danger'
      },
      {
        text: 'Contract Disputed (Termination Asked by Customer)',
        cssClass: 'danger'
      },
      {
        text: 'Contract Terminated',
        cssClass: 'danger'
      },
      {
        text: 'Contract Finished',
        cssClass: 'info'
      }
    ];

    linker = function(scope, element, attrs) {
      scope.$watch('contractState', function(newValue, oldValue) {
        scope.state = contractStates[newValue];
      }, true);
    };

    return {
      restrict: 'A',
      scope: {
        contractState: '='
      },
      template: '<div class="contracts-alert contracts-alert-{{state.cssClass}}">{{state.text}}</div>',
      link: linker
    };
  });