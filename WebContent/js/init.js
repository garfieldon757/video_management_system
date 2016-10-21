/**
 * @description
 * Initialize each HT property module on document ready.
 */

var MODULES = [
        'NAVIGATION'
    ];

$(function () {
    'use strict';

    MODULES.forEach(function (module) {
      if (HT[module]) {
        HT[module].init();
      }
    });
});