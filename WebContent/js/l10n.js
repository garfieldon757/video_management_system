'use strict';

angular.module('l10n', [])
  .factory('$languages', function($http, $q) {
    var d = $q.defer();
    $http.get('/api/l10n/languages')
      .success(function(data) {
        d.resolve(data);
      })
      .error(function() {
        d.resolve({'_error_': 'Error loading languages!'});
      });

    return d.promise;
  })
  .factory('$countries', function($http, $q) {
    var d = $q.defer();
    $http.get('/api/l10n/countries')
      .success(function(data) {
        d.resolve(data);
      })
      .error(function() {
        d.resolve({'_error_': 'Error loading countries!'});
      });

    return d.promise;
  });
