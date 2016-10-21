var HT = HT || {};

HT.LEAD_CAPTURE = (function() {
  'use strict';

  /**
   * @description
   * Initialize LEAD_CAPTURE module.
   *
   * @return void
   * @public
   */
  function init() {
    _initForms();
  }

  function _initForms() {
    var forms = $('.js-lead-capture-form');
    var controls = $('input,button', forms);
    var thanks = $('.js-lead-capture-form-thanks');

    thanks.hide();

    forms.validator().on('submit', function(e) {
      if (!e.isDefaultPrevented()) {
        e.preventDefault();

        var self = $(this);
        var button = $('button', this);
        var label = button.text();
        var data = $(this).serialize();

        button.text('Processing...');
        controls.prop('disabled', true);

        $.post('/api/forms/lead-capture', data)
          .done(function() {
            forms.each(function() {
              var parent = $(this).parent();

              parent.height(parent.height());
            });

            forms.hide();
            thanks.fadeIn();

            if (typeof goog_report_conversion === 'function')
              goog_report_conversion();
          })
          .fail(function(err) {
            if (err.status === 400 && err.responseJSON) {
              var validator = self.data('bs.validator');

              if (err.responseJSON.email === 'invalid') {
                var email = $('[name="email"]', self);

                email.data('bs.validator.errors', ['The email address is invalid.']);
                validator.showErrors(email);
              }
            }

            button.text(label);
            controls.prop('disabled', false);
          });
      }
    });
  }

  /**
   * @description
   * LEAD_CAPTURE module API.
   *
   * @public
   */
  return {
    init: init
  };
})();

$(function() {
  HT.LEAD_CAPTURE.init();
});



