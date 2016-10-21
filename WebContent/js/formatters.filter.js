'use strict';

angular
  .module('formatters', [])
  .filter('formatArray', function() {
    return function(value, separator, prop) {
      var array = value;

      if (separator === undefined)
        separator = ', ';

      if (prop) {
        array = array
          .filter(function(item) {
            return item && item[prop] !== undefined && item[prop] !== null;
          })
          .map(function(item) {
            return item[prop];
          });
      }

      return array.join(separator);
    };
  })
  .filter('formatBool', function() {
    return function(value) {
      return value ? 'Yes' : 'No';
    };
  })
  .filter('capitalize', function() {
    return function(value) {
      return _.capitalize(value || '-')
    };
  })
  .filter('durationMinutes', function() {
    return function(value) {
      var h = Math.floor(value / 60);
      var m = value % 60;

      return m ? (h + ' h ' + m + ' min') : (h + ' hours');
    };
  })
  .filter('durationDays', function() {
    return function(value) {
      return Math.floor(value / 1440) + ' days';
    };
  })
  .filter('duration', function($filter) {
    return function(value, prop) {
      var minutes, type;

      if (prop && value && value.hasOwnProperty(prop)) {
        minutes = value[prop];

        if (value.schedule && value.schedule.type) // contract
          type = value.schedule.type;
        else if (value.type) // payment
          type = value.type;
      }

      if (isNaN(minutes))
        return '';

      return $filter(type === 'li' ? 'durationDays' : 'durationMinutes')(minutes);
    };
  })
  .filter('rate', function() {
    return function(value) {
      var rate, liveIn;

      if (value) {
        if (value.hasOwnProperty('rate') && value.hasOwnProperty('schedule') && value.schedule.type) { // contract
          rate = value.rate;
          liveIn = value.schedule.type === 'li';
        }
        else if (value.hasOwnProperty('conciergeCarerProfile') && value.conciergeCarerProfile.hasOwnProperty('rate')) { // carer
          rate = value.conciergeCarerProfile.rate;
          liveIn = (value.conciergeCarerProfile.premiumFeatures && value.conciergeCarerProfile.premiumFeatures.liveIn);
        }
      }

      if (isNaN(rate))
        return '';

      return '£' + rate + ' per ' + (liveIn ? 'day' : 'hour');
    };
  })
  .filter('moduleName', function() {
    var MODULE = {
      'concierge-care-service': 'Care service',
      'concierge-contract': 'Contract',
      'concierge-happiness-state': 'Happiness state',
      'concierge-payment': 'Payment',
      'concierge-spoken-language': 'Spoken language',
      'interest': 'Interest',
      'medical-condition': 'Medical condition',
      'message': 'Message',
      'review': 'Review',
      'session': 'Session',
      'support-need': 'Support need',
      'support-user': 'Support user',
      'user': 'User',
      'user-picture': 'User picture',
      'website-config': 'Website config',
      'note': 'Note',
      'faq': 'FAQ',
      'careers': 'Careers'
    };

    return function(value) {
      return MODULE[value] || '[Unknown]';
    };
  });
