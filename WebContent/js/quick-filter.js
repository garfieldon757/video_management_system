HT.QuickFilter = (function($) {
  'use strict';

  function _bindEvents() {
    // HT-533
    $(".ht-quick-filter__wrapper .dropdown").each(function() {
      var dropDown = $(this);
      var buttonLabel = dropDown.find('.ht-quick-filter__button .ht-quick-filter__label');
      var inputHidden = dropDown.find('input[type=hidden]');

      // ht-688 (Quick filter drop down should not show selected entry)
      dropDown.on('click', '.dropdown-toggle', function() {
        if(!dropDown.hasClass('open')) {
          var lis = dropDown.find('li');
          lis.removeClass('hide');
          var label = dropDown.find('.-js-label').text();
          lis.each(function() {
            var li = $(this);
            if(li.text() === label) {
              li.addClass('hide');
            }
          });
        }
      });
      dropDown.on('click', 'li', function() {
        var el = $(this);
        var text = el.text();
        buttonLabel.text(text);
        var value = el.data('value') || text;
        if(value === '--') {
          value = '';
        }
        inputHidden.val(value);
        inputHidden.attr('name', el.data('group'));
      });
    });
  }

  function init() {
    _bindEvents();
  }

  return {
    init: init
  }

})(jQuery);

$(function() {
  HT.QuickFilter.init();
});

