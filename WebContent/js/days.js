'use strict';

(function() {
  var names = {
    mon: 'Monday',
    tue: 'Tuesday',
    wed: 'Wednesday',
    thu: 'Thursday',
    fri: 'Friday',
    sat: 'Saturday',
    sun: 'Sunday'
  };

  angular.module('days', [])
    .constant('DAYS', ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'])
    .filter('day', function() {
      return function(str) {
        return names[str] || '';
      };
    });
}());