'use strict';

angular.module('htApp')
  .controller('ConciergeInstructCtrl', function($rootScope, $scope, $location, $state, $stateParams, $q, DAYS, Utils, Alerts, CarerUser, ConciergeContracts, ConciergeCareService) {
    var info = $scope.info = {
      days: DAYS,
      viewState: 'loading',
      submitted: false,
      tosAgreed: false,
      validatedContract: null,
      scheduleValid: true,
      startDate: new Date(),
      endDate: null,
      repeat: 1,
      repeatOptions: Utils.contractRepeatOptions,
      liveIn: false
    };

    $scope.messageCarer = function(carer) {
      $state.go('messages.send', {
        back: '/concierge/contracts',
        id: carer._id,
        name: carer.name,
        contract: $scope.contractId
      });
    };

    // Checks if the user id is set, and tries to find user profile to show
    function checkForUser() {
      $q.all([
          CarerUser.get({id: $stateParams.id}).$promise,
          ConciergeCareService.query().$promise,
          ConciergeContracts.last().$promise
        ])
        .then(function(responses) {
          var carer = responses[0];
          var services = responses[1];
          var location = responses[2] ? responses[2].location : null;

          info.liveIn = carer.conciergeCarerProfile.premiumFeatures && carer.conciergeCarerProfile.premiumFeatures.liveIn;

          $scope.contract = {
            carer: carer._id,
            rate: carer.conciergeCarerProfile.rate,
            duties: [],
            schedule: info.liveIn ? {
              type: 'li',
              days: {li: {}}
            } :
            {
              type: 'w',
              days: {w: {}}
            },
            location: location ? angular.copy(location) : {}
          };

          // use skills from concierge care services
          carer.conciergeCarerProfile.skills = [];
          for (var i = 0; i < services.length; i++)
            carer.conciergeCarerProfile.skills.push(services[i].name);

          $scope.carer = carer;

          checkSchedule($scope.contract.schedule);

          $scope.$watch('contract.schedule', checkSchedule, true);
          $scope.setViewState('form');
        })
        .catch(function(err) {
          console.error(err);
          $scope.setViewState('error');
        });
    }

    $scope.toggleDuty = function(duty) {
      var index = $scope.contract.duties.indexOf(duty);

      if (index >= 0)
        $scope.contract.duties.splice(index, 1);
      else
        $scope.contract.duties.push(duty);
    };

    $scope.addHoursToDay = function(day) {
      if (!day.hours)
        day.hours = [];

      var max = 0;
      var start = 8;
      var end = 10;

      for (var i = 0; i < day.hours.length; i++) {
        var slot = parseSlot(day.hours[i]);

        if (slot)
          max = Math.max(max, slot.e);
      }

      if (max > 0 && max < 24) {
        start = max;
        end = max + 1;
      }

      day.hours.push({
        s: moment.utc(start * 3600 * 1000).format('HH:mm'),
        e: moment.utc(end * 3600 * 1000).format('HH:mm')
      });
    };

    $scope.removeHoursFromDay = function(day, index) {
      day.splice(index, 1);
    };

    $scope.toggleTimes = function(day) {
      if (!$scope.contract.schedule.days.w)
        $scope.contract.schedule.days.w = {};

      if ($scope.contract.schedule.days.w[day])
        delete $scope.contract.schedule.days.w[day];
      else {
        $scope.contract.schedule.days.w[day] = {
          hours: [
            {s: '08:00', e: '10:00'}
          ]
        };
      }
    };

    $scope.toggleDay = function(day) {
      if (!$scope.contract.schedule.days.li)
        $scope.contract.schedule.days.li = {};

      if ($scope.contract.schedule.days.li[day])
        $scope.contract.schedule.days.li[day] = false;
      else
        $scope.contract.schedule.days.li[day] = true;
    };

    function parseTime(time) {
      time = moment(time, 'HH:mm', true);

      return time.isValid() ? (time.hour() + (time.minute() / 60)) : 0;
    }

    function parseSlot(slot) {
      if (!slot.s || !slot.e)
        return null;

      var start = parseTime(slot.s);
      var end = parseTime(slot.e);

      // allow slots over night
      if (end < start)
        end += 24;

      return {s: start, e: end};
    }

    function dayValid(day, used, index) {
      var overnight = index !== undefined;
      var pad = overnight ? (index * 24) : 0;
      var padWeek = 168; //7 * 24;

      day.error = false;
      for (var i = 0; i < day.hours.length; i++) {
        var slot = parseSlot(day.hours[i]);

        if (!slot)
          continue;

        var start = pad + slot.s;
        var end = pad + slot.e;

        for (var j = 0; j < used.length; j++) {
          var item = used[j];

          if (start < item.e && end > item.s)
            day.error = true;
        }

        used.push({s: start, e: end});

        // add hours of monday a week after to count for overlaps with sunday
        if (overnight && index === 0)
          used.push({s: slot.s + padWeek, e: slot.e + padWeek});
      }

      return !day.error;
    }

    function checkSchedule(schedule) {
      var valid = true;
      var used = [];

      if (schedule.type == 'li')
        schedule.days.weekNotEntered = !_.some(schedule.days.li);
      else {
        var count = 0;

        for (var i = 0; i < DAYS.length; i++) {
          var dayId = DAYS[i];
          var day = schedule.days.w[dayId];

          if (day) {
            count++;

            // we want to always call dayValid() to populate 'used', so put it first in condition
            valid = dayValid(day, used, i) && valid;
          }
        }

        schedule.days.weekNotEntered = (count === 0);
      }

      info.scheduleValid = (!schedule.days.weekNotEntered && valid);
    }

    $scope.setViewState = function(view) {
      if (info.viewState !== view) {
        info.viewState = view;
      }
    };

    $scope.formErrors = function(form) {
      var errors = [];
      var hasRequired = false;
      var hasInvalidTimes = false;
      var hasInvalidFields = false;

      angular.forEach(form.$error, function(fields, error) {
        if (error === 'required')
          hasRequired = true;
        else {
          angular.forEach(fields, function(field) {
            var name = field.$name;

            if (name.substr(0, 5) === 'hours')
              hasInvalidTimes = true;
            else
              hasInvalidFields = true;
          });
        }
      });

      if (hasRequired)
        errors.push('Fill in all required fields');

      if (hasInvalidTimes)
        errors.push('Fix invalid times');

      if (hasInvalidFields)
        errors.push('Fix invalid fields');

      return errors;
    };

    $scope.valid = function(form) {
      return info.tosAgreed && info.scheduleValid && !!$scope.contract.duties.length && form.$valid;
    };

    $scope.validate = function(form) {
      var contract = $scope.contract;

      info.submitted = true;

      // If user didn't agree to TOS or if there is an error
      if (!$scope.valid(form))
        return;

      contract.start = moment(info.startDate).format('YYYY-MM-DD');

      if (info.repeat == -1)
        contract.schedule.repeat = (moment(info.endDate).diff(moment(info.startDate), 'days') + 1) / 7;
      else
        contract.schedule.repeat = info.repeat;

      $scope.save(true);
    };

    $scope.save = function(validateOnly) {
      var contract = $scope.contract;

      ConciergeContracts.save({validateOnly: validateOnly}, contract).$promise
        .then(function(data) {
          $scope.contractId = data._id;
          info.validatedContract = data;

          $scope.setViewState(validateOnly ? 'summary' : 'thanks');
        })
        .catch(function(err) {

          if (err.data.error == 'disputed') {
            Alerts.error('You have contracts in dispute with this carer.');
          }
          else if (err.data.schedule) {
            Alerts.error('The schedule you have entered is invalid');
          } else {
            $scope.errors = [];

            for (var prop in err.data) {

              var msg = err.data[prop].message;
              if (msg) {
                prop = prop.replace(/\./g, '_');
                $scope.errors[prop] = msg;
              }
            }

            Alerts.error('Operation failed.');
          }
        });
    };

    $scope.startDateChanged = function() {
      if (!info.endDate || moment(info.endDate).isBefore(info.startDate, 'day'))
        info.endDate = moment(info.startDate).toDate();
    };

    // Checks for user ID and gets it from the API
    checkForUser();
  });