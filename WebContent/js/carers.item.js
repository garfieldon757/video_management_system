'use strict';

angular.module('htApp')
  .controller('ConciergeCarersItemCtrl', function($uiViewScroll, CONFIG, $location, $state, Utils, Pagination, Reviews, reviews, carer) {
    var conciergeCarersItemCtrl = this;

    angular.extend(conciergeCarersItemCtrl, {
      pager: new Pagination(CONFIG.reviewsItemsPerPage, reviews.itemCount, reviews.page),
      reviews: reviews.reviews,
      carer: carer,
      videoEmbedURL: Utils.videoEmbedURL(carer.conciergeCarerProfile.video),

      updateReviews: function() {
        Reviews.paginate({
          user: conciergeCarersItemCtrl.carer._id,
          limit: CONFIG.reviewsItemsPerPage,
          page: conciergeCarersItemCtrl.pager.currentPage
        }).$promise
          .then(function(response) {
            conciergeCarersItemCtrl.pager.totalItems = response.itemCount;
            conciergeCarersItemCtrl.reviews = response.reviews;
          })
          .then(function() {
            $uiViewScroll($('#reviews'));
          })
          .catch(function(err) {
            error(err);
          });
      },

      messageCarer: function(carer) {
        $state.go('messages.send', {back: $location.path(), id: carer._id, name: carer.name, concierge: true});
      }
    });

    if (carer.conciergeCarerProfile.premiumFeatures) {
      var features = carer.conciergeCarerProfile.premiumFeatures;

      angular.extend(conciergeCarersItemCtrl, {
        workedHours: features.workedHours.total,
        availability: moment(features.back).isAfter(moment(), 'days') ? 'no' : (features.limitedAvailability ? 'limited' : 'yes'),
        carOrLicence: features.car ? 'car' : (features.drivingLicence ? 'licence' : 'none')
      });
    }
  });
