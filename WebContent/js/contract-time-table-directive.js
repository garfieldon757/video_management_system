'use strict';

angular.module('htApp')
  .directive('contractTimeTable', function() {
    return {
      restrict: 'A',
      scope: true,
      bindToController: {
        contract: '=contractTimeTable',
        title: '=title',
        config: '=config'
      },
      controllerAs: 'contractTimeTable',
      templateUrl: '/public/app/concierge/contracts/contract-time-table.html',
      controller: function(ScheduleWeek) {
        var contractTimeTable = this;

        angular.extend(contractTimeTable, {
          scheduleWeek: new ScheduleWeek(contractTimeTable.contract)
        });
      }
    };
  });