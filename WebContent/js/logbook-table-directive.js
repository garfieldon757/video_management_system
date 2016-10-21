'use strict';

angular.module('htApp')
  .directive('logbookTable', function($location, ConciergeVisitsSummary, Alerts) {
    var dummyData,
      dummyMonthlylyData,
      linker,
      apiDateFormat = 'YYYY-MM-DD',
      dateFormat = 'MMM DD',
      timeFormat = 'HH:mm';

    function prepareData(data, start, end) {
      var services = {},
        hours,
        day,
        item,
        duration,
        startClone,
        i;

      data.hasServices = false;

      for (i = 0; i < data.length; i++) {
        var entry = data[i];

        hours = {
          s: moment(entry.start),
          e: moment(entry.end)
        };

        if (hours.s.isBefore(start) || hours.s.isAfter(end)) {
          return;
        }

        item = hours.s.format(dateFormat) + '<br>' +
               hours.s.format(timeFormat) + ' - ' + hours.e.format(timeFormat),

          duration = hours.e.diff(hours.s, 'hours', true);

        day = hours.s.diff(start, 'days');

        entry.services.forEach(function(service) {
          if (!services[service]) {
            services[service] = {
              name: service,
              days: [
                {am: false, pm: false},
                {am: false, pm: false},
                {am: false, pm: false},
                {am: false, pm: false},
                {am: false, pm: false},
                {am: false, pm: false},
                {am: false, pm: false}
              ]
            };
            data.hasServices = true;
          }

          if (hours.s.hours() < 12 && (hours.s.hours() + duration) > 12) {
            // Service started at AM at ended PM
            services[service].days[day].am =
              hours.s.format(dateFormat) + '<br>' + hours.s.format(timeFormat) + ' - 12:00';

            services[service].days[day].pm =
              hours.s.format(dateFormat) + '<br>12:00 - ' + hours.e.format(timeFormat);
          }
          else if (hours.s.hours() < 12) {
            services[service].days[day].am = item;
          }
          else {
            services[service].days[day].pm = item;
          }
        });
      }

      startClone = start.clone();

      data.days = [startClone.format(dateFormat)];
      for (i = 0; i < 6; i++) {
        data.days.push(startClone.add(1, 'days').format(dateFormat));
      }

      data.services = services;
      return data;
    }

    linker = function(scope, element, attrs) {
      var params;

      // Time period
      scope.start = moment().day(1).startOf('day');
      scope.end = moment().day(7).endOf('day');
      scope.datePeriodLabel = scope.start.format(dateFormat) + ' - ' + scope.end.format(dateFormat);

      function getVisits(contract) {
        var params = {
          from: scope.start.format(apiDateFormat),
          to: scope.end.format(apiDateFormat)
        };

        if (contract) {
          params.contract = contract;
        }

        ConciergeVisitsSummary
          .query(params).$promise
          .then(function(data) {
            scope.logbookTable = prepareData(data, scope.start, scope.end);
          })
          .catch(function(err) {
            console.error(err);
            Alerts.error('Error loading data.');
          })
          .finally(function() {
            $scope.loading = false;
          });
      }

      scope.changePeriod = function(period) {
        scope.start.add(period, 'days');
        scope.end.add(period, 'days');
        scope.datePeriodLabel = scope.start.format(dateFormat) + ' - ' + scope.end.format(dateFormat);

        getVisits(attrs.lbContract)
      };

      getVisits(attrs.lbContract);

      scope.showViewButton = true;

      if ($location.path() == '/concierge/logbook') {
        scope.showViewButton = false;
      }
      scope.showNavigation = attrs.lbShowNavigation;

    };

    return {
      restrict: 'A',
      templateUrl: '/public/app/concierge/logbook/logbook-table.html',
      link: linker
    };
  });