'use strict';

(function() {
  var ranges = [
    {min: -Infinity, max: 0, value: 0, name: '0'},
    {min: 1, max: 4, value: 1, name: '1-4'},
    {min: 5, max: 9, value: 5, name: '5-9'},
    {min: 10, max: Infinity, value: 10, name: '10+'}
  ];

  angular.module('experience', [])
    .constant('EXPERIENCE', ranges)
    .filter('experience', function() {
      return function(value) {
        value = value || 0;
        if (!isNaN(value)) {
          value = parseInt(value);
          for (var i = 0; i < ranges.length; i++) {
            var range = ranges[i];
            if (value >= range.min && value <= range.max)
              return i === 0 ? 'No experience' : (range.name + ' years experience');
          }
        }

        return '';
      };
    });
}());