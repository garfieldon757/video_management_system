'use strict';

angular.module('uiViewSmoothScroll', [])
  .config(function($provide) {
    $provide.decorator('$uiViewScroll', function($delegate, $timeout) {
      var timeout = null;

      return function($element, options) {
        if (!$element || !$element.offset())
          return;

        var events = 'scroll wheel DOMMouseScroll mousewheel touchmove';
        options = options || {};

        // HT-442
        var headerHeight = $('.ht-header').height() || 0;

        var root = document.documentElement.scrollTop ? document.documentElement : document.body;
        var top = $element.offset().top;
        var visibleTop = top - root.scrollTop;
        var height = angular.element(window).height() / 2;

        if (visibleTop < 0 || visibleTop > height / 2) {

          if (timeout)
            $timeout.cancel(timeout);

          timeout = $timeout(function() {
            var scrollTopStart = $(window).scrollTop(); // root.scrollTop;
            var scrollTopEnd = options.toTop ? 0 : top - 20 - headerHeight;
            var animationTime = 450;

            var canceled = false;
            var page = $('html, body');
            page.on(events, function() {
              canceled = true;
              page.off(events);
            });

            var startTime = +new Date();

            var animate = function () {
              var time = +new Date();
              // interpolate
              var alpha = Math.min(1, (time - startTime)/animationTime);
              var invAlpha = 1 - alpha;
              var scrollTop = invAlpha * scrollTopStart + alpha * scrollTopEnd;
              $(window).scrollTop(scrollTop);

              $timeout(function() {
                if(alpha < 1 && !canceled) {
                  requestAnimationFrame(animate);
                }
                else {
                  timeout = null;
                }
              }, 1000 / 60);
            };
            $timeout(animate);
          }, 0, false);
        }
      };
    });
  });
