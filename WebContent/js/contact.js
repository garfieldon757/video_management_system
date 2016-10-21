var HT = HT || {};

HT.CONTACT = (function() {
  'use strict';

  /**
   * @description
   * Initialize CONTACT module.
   *
   * @return void
   * @public
   */
  function init() {
    _initForms();
  }

  function _initForms() {
    var forms = $('.js-contact-form');
    var controls = $('textarea,input,button', forms);

    forms.validator().on('submit', function(e) {
      if (!e.isDefaultPrevented()) {
        e.preventDefault();

        var self = $(this);
        var button = $('button', this);
        var label = button.text();
        var data = $(this).serialize();

        button.text('Processing...');
        controls.prop('disabled', true);

        $.post('/api/forms/contact', data)
          .done(function() {
            forms.parent().addClass('submitted');
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
   * CONTACT module API.
   *
   * @public
   */
  return {
    init: init
  };
})();

$(function() {
  HT.CONTACT.init();
});



