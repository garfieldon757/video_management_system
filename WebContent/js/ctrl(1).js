'use strict';

angular.module('htApp')
  .controller('BannerCtrl', function($scope, $rootScope, $cookieStore) {
    var user = $rootScope.currentUser;
    var bannerCookieName = 'hide-banner-' + user._id;

    $scope.showBanner = (user.isCarer || user.isClient) && !$cookieStore.get(bannerCookieName);

    $scope.setConciergeBannerCookie = function() {
      $scope.showBanner = false;
      $cookieStore.put(bannerCookieName, true);
    };
  });