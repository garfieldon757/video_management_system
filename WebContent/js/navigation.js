HT.NAVIGATION = (function ($, $window, $document) {
  'use strict';

    // menu DOM wrapper
    var $MENU = $('.ht-menu') || null;
    // slider DOM wrapper
    var $SLIDER = $('.ht-slider') || null;

    /**
     * @description
     * Toggle elem's "active" class.
     *
     * @return {Function}
     * @private
     */
    function _toggleElem () {
      $(this).parents('.ht-toggle').toggleClass('active').closest('.js-toggle-filter').toggleClass('filter-active')
    }

    /**
     * @description
     * Toggle map menu's "active" class.
     *
     * @private
     */
    function _toggleMapMenu () {
      $(this).toggleClass('active');
    }

    /**
     * @description
     * Toggle navigation menu's "active" class.
     *
     * @private
     */
    function _toggleNavMenu () {
      $('html').toggleClass('pop-up-active');
      $('.js-menu').toggleClass('active');
      $MENU.toggleClass('active');
    }

    /**
     * @description
     * Go one browser page back.
     *
     * @return {Function}
     * @private
     */
    function _pageBack () {
      window.location = window.location.protocol + "//" + window.location.host
    }

    /**
     * @description
     * Initialize slick slider.
     *
     * @return {Object}
     * @private
     */
    function _initializeSlider () {
      $SLIDER.slick({
        arrows: false,
        dots: true,
        autoplay: true,
        autoplaySpeed: 15000
      });
    }

    /**
     * @description
     * Bind events on page load.
     *
     * @private
     */
     function _bindEvents () {
      if ($SLIDER.length) {
        _initializeSlider();
      }
      $document.on('click', '.js-menu', _toggleNavMenu);
      $document.on('click', '.js-map-menu', _toggleMapMenu);
      $document.on('click', '.js-toggle', _toggleElem);
      $document.on('click', '.js-return', _pageBack);
    }

    /**
     * @description
     * Init fn.
     *
     * @return void
     * @public
     */
     function init () {
      _bindEvents();
    }

    /**
     * @description
     * Navigation module API
     *
     * @public
     */
     return {
      init: init
    };
  })(jQuery, HT.window, HT.document);