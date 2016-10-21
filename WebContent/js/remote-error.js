'use strict';

angular.module('remoteError', [])
/**
 * Removes server error when user updates input
 */
  .directive('remoteError', function() {
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function(scope, element, attrs, ngModel) {
        scope.$watch(attrs.ngModel, function() {
          return ngModel.$setValidity('remote', true);
        });

        if (attrs.remoteError) {
          var props = attrs.remoteError.split(/\s*,\s*/);
          props.forEach(function(prop) {
            scope.$watch(prop, function() {
              return ngModel.$setValidity('remote', true);
            });
          });
        }
      }
    };
  });