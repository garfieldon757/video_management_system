HT.GOOGLE_MAP = (function ($) {
  'use strict';

  // Google map DOM wrapper.
  var MAP_WRAPPER = document.getElementById('ht-map') || null;

  // Google map carers' DOM wrappers.
  var $CARERS = $('.js-map-carer li') || [];
  var markers = [];

  var styles = [
    {
      "featureType": "administrative.country",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "administrative.province",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "administrative.locality",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "administrative.neighborhood",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "administrative.land_parcel",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "landscape.man_made",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#f8f7f4"
      }
      ]
    },
    {
      "featureType": "landscape.man_made",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "landscape.natural.landcover",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "landscape.natural.terrain",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.attraction",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.attraction",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.business",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.business",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.government",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.government",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.medical",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.medical",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.park",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#d6eac0"
      }
      ]
    },
    {
      "featureType": "poi.park",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.place_of_worship",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.place_of_worship",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.school",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.school",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "poi.sports_complex",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e3e3e3"
      }
      ]
    },
    {
      "featureType": "poi.sports_complex",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "road.highway",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#fcd8d3"
      }
      ]
    },
    {
      "featureType": "road.highway",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    },
    {
      "featureType": "road.arterial",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#ffffff"
      }
      ]
    },
    {
      "featureType": "road.arterial",
      "elementType": "geometry.stroke",
      "stylers": [
      {
        "color": "#ff0000"
      },
      {
        "visibility": "off"
      }
      ]
    },
    {
      "featureType": "road.local",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#ff0000"
      }
      ]
    },
    {
      "featureType": "road.local",
      "elementType": "geometry.stroke",
      "stylers": [
      {
        "color": "#ffffff"
      }
      ]
    },
    {
      "featureType": "water",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "hue": "#00b8ff"
      }
      ]
    },
    {
      "featureType": "water",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "color": "#7a8a8e"
      }
      ]
    }
  ];

  /**
   * @description
   * Get styled google map.
   *
   * @return {Object}
   * @private
   */
   function _getMapStyles () {
    return new google.maps.StyledMapType(styles, {name: 'HomeTouch'});
  }

  /**
   * @description
   * Get google map config.
   *
   * @return {Object}
   * @private
   */
  function _getMapConfig() {
    var $map = $(MAP_WRAPPER);

    return {
      center: new google.maps.LatLng($map.data('lat'), $map.data('lng')),
      zoom: 11,
      scrollwheel: false,
      mapTypeControlOptions: {
        mapTypeIds: [google.maps.MapTypeId.ROADMAP, 'HTStyle']
      }
    };
  }

  /**
   * @description
   * Set marker for each carer.
   *
   * @param {Object} map
   * @return {Object}
   * @private
   */
  function _setMarkers(map) {
    $.get('/api/concierge/carers/map', function(data) {
      if (data && data.length) {
        for (var i = 0; i < data.length; i++) {
          var item = data[i];

          var marker = new google.maps.Marker({
            position: new google.maps.LatLng(item.loc[1], item.loc[0]),
            title: item.name,
            map: map
          });

          item.url = '/carers/' + item.slug;
          marker.set('carer', item);

          marker.addListener('click', function() {
            window.location = this.get('carer').url;
          });

          markers.push(marker);
        }
      }
    });
  }

  function _sortMarkers(center) {
    for (var i = 0; i < markers.length; i++)
      markers[i].distance = google.maps.geometry.spherical.computeDistanceBetween(center, markers[i].getPosition());

    markers.sort(function _sortByDist(a, b) {
      return (a.distance - b.distance);
    });
  }

  /**
   * @description
   * Initialize google map.
   *
   * @return {Object}
   * @public
   */
  function init() {
    var dragEndTimer = null;

    if (MAP_WRAPPER) {
      var mapStyles = _getMapStyles();
      var map = new google.maps.Map(MAP_WRAPPER, _getMapConfig());

      _setMarkers(map);

      map.mapTypes.set('HTStyle', mapStyles);
      map.setMapTypeId('HTStyle');

      map.addListener('dragstart', function() {
        if (dragEndTimer) {
          window.clearTimeout(dragEndTimer);
          dragEndTimer = null;
        }
      });

      map.addListener('dragend', function() {
        dragEndTimer = window.setTimeout(function() {
          dragEndTimer = null;

          _sortMarkers(map.getCenter());

          $CARERS.each(function(index) {
            var carer = markers[index].get('carer');

            $('img', this).attr('src', '/api/users/' + carer.id + '/picture');
            $('.user .name', this).text(carer.name);
            $('.user .location', this).text(carer.borough);
            $('.user .price', this).text('£' + carer.rate + ' / ' + carer.rateUnit);
          });
        }, 1000);
      });
    }
  }

  /**
   * @description
   * Google map API.
   *
   * @public
   */
   return {
    init: init
  };
})(jQuery);