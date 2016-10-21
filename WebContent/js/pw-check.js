'use strict';

/**
 * Directive for checking that passwords match.
 *
 * Source: http://blog.brunoscopelliti.com/angularjs-directive-to-check-that-passwords-match
 */
angular.module('pwCheck', [])
  .directive('pwCheck', [
    function() {
      return {
        require: 'ngModel',
        link: function(scope, elem, attrs, ctrl) {
          var firstPassword = '#' + attrs.pwCheck;
          elem.add(firstPassword).on('keyup', function() {
            scope.$apply(function() {
              var v = elem.val() === $(firstPassword).val();
              ctrl.$setValidity('pwmatch', v);
            });
          });
        }
      }
    }
  ]);
