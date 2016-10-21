'use strict';

angular
  .module('htApp', [
    'htConfig',
    'htModels',
    'utils',
    'auth',
    'alerts',
    'remoteError',
    'l10n',
    'pwCheck',
    'pagination',
    'days',
    'experience',
    'formatters',
    'scheduleWeek',
    'intercom',

    'ngResource',
    'ngSanitize',
    'ngAnimate',
    'ui.router',
    'ui.bootstrap',
    'ui.mask',
    'uiViewSmoothScroll', // must be loaded after ui.bootstrap
    'mgcrea.ngStrap.tooltip',
    'mgcrea.ngStrap.datepicker',
    'mgcrea.ngStrap.timepicker',
    'mgcrea.ngStrap.popover',
    'mgcrea.ngStrap.modal',
    'checklist-model',
    'ngFileUpload',
    'intercomCommunications',
    'angular.filter'
  ])
  .config(function($sceDelegateProvider, $stateProvider, $urlRouterProvider, $httpProvider, $datepickerProvider, $timepickerProvider, $bsModalProvider, paginationConfig, CONFIG) {
    $sceDelegateProvider.resourceUrlWhitelist([
      'self',
      'https://www.youtube.com/embed/**'
    ]);

    // Stripe
    Stripe.setPublishableKey(CONFIG.stripePublishable);

    // angular-strap defaults
    angular.extend($datepickerProvider.defaults, {
      dateFormat: CONFIG.dateFormat,
      autoclose: true
    });

    angular.extend($timepickerProvider.defaults, {
      timeFormat: CONFIG.timeFormat,
      autoclose: false
    });

    angular.extend($bsModalProvider.defaults, {
      templateUrl: '/public/app/templates/modal.html'
    });

    paginationConfig.previousText = '';
    paginationConfig.nextText = '';

    $urlRouterProvider
      .when('/settings', '/settings/profile')
      .otherwise('/');

    $stateProvider
      .state('home', {
        url: '/',
        template: '',
        controller: 'HomeCtrl',
        allowed: true
      })
      .state('messages', {
        url: '/messages',
        templateUrl: '/public/app/messages/messages.html',
        controller: 'MessagesCtrl as messagesCtrl',
        allowed: true,
        layout: '2c',
        requireClient: true
      })
      .state('messages.send', {
        url: '/send/:user_id?back&id&name&contract&concierge',
        params: {
          user_id: {
            value: null,
            squash: true
          }
        },
        templateUrl: '/public/app/messages/send/messages.send.html',
        controller: 'MessagesSendCtrl as messagesSendCtrl',
        resolve: {
          contact: function(Contact, $rootScope) {
            return Contact.query({user_id: $rootScope.currentUser._id}).$promise;
          },
          message: function() {
            return {};
          }
        },
        allowed: true,
        layout: '2c',
        requireClient: true
      })
      .state('messages.reply', {
        url: '/reply/:id',
        templateUrl: '/public/app/messages/send/messages.send.html',
        controller: 'MessagesSendCtrl as messagesSendCtrl',
        resolve: {
          contact: function(Contact, $rootScope) {
            return Contact.query({user_id: $rootScope.currentUser._id}).$promise;
          },
          message: function($stateParams, MessageUtils) {
            return MessageUtils.createReply($stateParams.id);
          }
        },
        allowed: true,
        layout: '2c',
        requireClient: true
      })
      .state('messages.forward', {
        url: '/forward/:id',
        templateUrl: '/public/app/messages/send/messages.send.html',
        controller: 'MessagesSendCtrl as messagesSendCtrl',
        resolve: {
          contact: function(Contact, $rootScope) {
            return Contact.query({user_id: $rootScope.currentUser._id}).$promise;
          },
          message: function($stateParams, MessageUtils) {
            return MessageUtils.createForward($stateParams.id);
          }
        },
        allowed: true,
        layout: '2c',
        requireClient: true
      })
      .state('contacts-invite', {
        url: '/contacts/invite',
        templateUrl: '/public/app/contacts/main.html',
        controller: 'ContactsCtrl',
        allowed: {carer: true, client: true},
        layout: '2c'
      })
      .state('settings-profile', {
        url: '/settings/profile',
        templateUrl: '/public/app/settings/profile.html',
        controller: 'SettingsProfileCtrl',
        allowed: true,
        layout: '2c'
      })
      .state('settings-change-password', {
        url: '/settings/change-password',
        templateUrl: '/public/app/settings/change-password.html',
        controller: 'SettingsChangePasswordCtrl',
        allowed: true,
        layout: '2c'
      })
      .state('invite', {
        url: '/invite/:id',
        templateUrl: '/public/app/invite/main.html',
        controller: 'InviteCtrl',
        public: true
      })
      .state('concierge-carers', {
        url: '/concierge/carers',
        templateUrl: '/public/app/concierge/carers/carers.html',
        controller: 'ConciergeCarersCtrl as conciergeCarersCtrl',
        allowed: {consumer: true},
        layout: '2c'
      })
      .state('concierge-carers.item', {
        url: '/:id',
        templateUrl: '/public/app/concierge/carers/item/carers.item.html',
        controller: 'ConciergeCarersItemCtrl as conciergeCarersItemCtrl',
        allowed: {consumer: true},
        layout: '2c',
        resolve: {
          carer: function(CarerUser, $stateParams) {
            return CarerUser.get({id: $stateParams.id}).$promise;
          },
          reviews: function(CONFIG, Reviews, $stateParams) {
            return Reviews.paginate({user: $stateParams.id, limit: CONFIG.reviewsItemsPerPage}).$promise;
          }
        }
      })
      .state('concierge-instruct', {
        url: '/concierge/instruct/:id',
        templateUrl: '/public/app/concierge/instruct/main.html',
        controller: 'ConciergeInstructCtrl',
        allowed: {consumer: true},
        layout: '2c'
      })
      .state('concierge-contracts', {
        url: '/concierge/contracts',
        templateUrl: '/public/app/concierge/contracts/contracts.html',
        controller: 'ConciergeContractsCtrl as conciergeContractsCtrl',
        allowed: {consumer: true},
        layout: '2c',
        resolve: {
          contracts: function(ConciergeContracts) {
            return ConciergeContracts.query().$promise;
          }
        }
      })
      .state('concierge-contracts.item', {
        url: '/:id',
        templateUrl: '/public/app/concierge/contracts/item/contracts.item.html',
        controller: 'ConciergeContractsItemCtrl as conciergeContractsItemCtrl',
        allowed: {consumer: true},
        layout: '2c',
        resolve: {
          contract: function($stateParams, ConciergeContracts) {
            return ConciergeContracts.get({id: $stateParams.id}).$promise;
          },
          conciergePayments: function(ConciergePayments, $stateParams) {
            return ConciergePayments.get({contract: $stateParams.id, state: 0, type: 'in'}).$promise;
          }
        }
      })
      .state('concierge-billing', {
        url: '/concierge/billing',
        templateUrl: '/public/app/concierge/billing/main.html',
        controller: 'BillingCtrl',
        allowed: {consumer: true},
        layout: '2c'
      })
      .state('concierge-billing-pay', {
        url: '/concierge/billing/pay/:id',
        templateUrl: '/public/app/concierge/billing/pay.html',
        controller: 'PaymentCtrl',
        allowed: {consumer: true}
      })
      .state('concierge-billing-cancel', {
        url: '/concierge/billing/cancel/:id',
        templateUrl: '/public/app/concierge/billing/cancel.html',
        controller: 'PaymentCtrl',
        allowed: {consumer: true},
        layout: '2c'
      })
      .state('concierge-account', {
          url: '/concierge/account/setup-payment',
          templateUrl: '/public/app/concierge/account/setup.payment.html',
          controller: 'AccountController',
          allowed: {consumer: true},
      })
      .state('carer-dashboard', {
        url: '/carer/dashboard',
        templateUrl: '/public/app/concierge/dashboard/dashboard.html',
        controller: 'CarerDashboardCtrl as carerDashboardCtrl',
        allowed: {conciergeCarer: true},
        resolve: {
          inboxMessages: function($rootScope, $q, ConciergeMessages) {
            return ConciergeMessages.inbox({id: $rootScope.currentUser._id}).$promise;
          },
          sentMessages: function($rootScope, $q, ConciergeMessages) {
            return ConciergeMessages.sent({id: $rootScope.currentUser._id}).$promise;
          },
          pendingContracts: function($q, ConciergeContracts) {
            // 0 - PENDING contract
            //return ConciergeContracts.detailedView({state: [0]}).$promise;
            var d = $q.defer();
            d.resolve([]);
            return d.promise;
          },
          acceptedContracts: function($q, ConciergeContracts) {
            // 3 - ACCEPTED, 4 - ACTIVE contract
            //return ConciergeContracts.detailedView({state: [3,4]}).$promise;
            var d = $q.defer();
            d.resolve([]);
            return d.promise;
          }
        },
        layout: '1c'
      })
      .state('error', {
        url: '/error',
        templateUrl: '/public/app/templates/error.html',
        public: true
      });

    $httpProvider.interceptors.push(function($q, $window) {
      return {
        'responseError': function(response) {
          switch (response.status) {
            case 401:
              $window.location = '/app/login#/?back=' + encodeURIComponent($window.location.href);
              break;
          }

          return $q.reject(response);
        }
      };
    });
  })
  .run(function($rootScope, $http, $location, $state, $window, $bsModal, CONFIG, Alerts, Auth, CurrentClient) {
    // config
    $rootScope.config = CONFIG;

    // globals
    $rootScope.closeAlert = Alerts.close;

    // formisimo conversion script (on first login)
    $rootScope.formisimoConversionAdded = false;

    // utility for changing path in a view
    $rootScope.goTo = function(url) {
      $location.path(url);
    };

    $rootScope.logout = function(back) {
      Auth.logout(back);
    };

    // navigation
    $rootScope.isActive = function(route) {
      var path = $location.path();
      return route && (route === path || path.indexOf(route + '/') === 0);
    };

    // terms & conditions
    var tcScope = $rootScope.$new();
    tcScope.modalLarge = true;
    tcScope.modalInverse = true;

    $rootScope.$on('tc-tablet.show', function() {
      $("#tc-content").load("/terms-tabletapp #tc-content");
    });

    $rootScope.showTabletTerms = function() {
      $bsModal({
        scope: tcScope,
        prefixEvent: 'tc-tablet',
        contentTemplate: '/public/app/templates/terms-tabletapp-modal.html',
        show: true
      });
    };

    $rootScope.$on('tc-concierge.show', function() {
      $("#tc-content").load("/terms-caremarketplace #tc-content");
    });

    $rootScope.showConciergeTerms = function() {
      $bsModal({
        scope: tcScope,
        prefixEvent: 'tc-concierge',
        contentTemplate: '/public/app/templates/terms-caremarketplace-modal.html',
        show: true
      });
    };

    // utils
    $rootScope.sendMessageToClient = function() {
      if ($rootScope.currentClient && $rootScope.currentUser && !$rootScope.currentUser.isClient)
        $state.go('messages.send', {user_id: $rootScope.currentClient._id, back: $location.path()});
    };

    // Check if user is authorised to access the new route
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
      toState.fromState = fromState;
      toState.fromParams = fromParams;

      if (!toState.public) {
        if (!Auth.isLoggedIn())
          return Auth.login();
        else if (!Auth.isAuthorised(toState.allowed))
          return $location.path('/');
      }

      if (toState.requireClient && !CurrentClient.isSet())
        return $location.path('/');

      $rootScope.layout = toState.layout || '1c';
    });

    $rootScope.$on('$stateChangeError', function(event, toState, toParams, fromState, fromParams, error) {
      console.error(event);
      console.error(fromState);
      console.error(toState);
      console.error(toParams);
      console.error(fromParams);
      console.error(error);
    });

    // Google Analytics
    if (CONFIG.gaID) {
      (function(i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function() {
            (i[r].q = i[r].q || []).push(arguments)
          }, i[r].l = 1 * new Date();
        a = s.createElement(o),
          m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
      })($window, $window.document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

      $window.ga('create', CONFIG.gaID, (CONFIG.env == 'production' ? 'auto' : {'cookieDomain': 'none'}));

      $rootScope.$on('$stateChangeSuccess', function() {
        console.log('charger');
        var module = $location.path().split('/')[1];
        var search = $location.url().split('?')[1];

        // do not collect '/'
        if (!module)
          return;

        // remove 'back' param
        if (search) {
          search = search
            .split('&')
            .filter(function(param) {
              return param.substr(0, 5) !== 'back=';
            })
            .join('&');
        }

        var page = '/app/' + module + (search ? ('?' + search) : '');

        $window.ga('send', 'pageview', {
          'page': page,
          'title': module
        });
      });
    }
  })
  /**
   * Home controller redirects user based on his role.
   */
  .controller('HomeCtrl', function($rootScope, $window, $location, CONFIG, Auth, CurrentClient) {
    if (Auth.isAdmin())
      Auth.admin();
    else if (Auth.isConciergeCarer()) {
      $location.path('/carer/dashboard');
    }
    else if (CurrentClient.isSet()) {
      if ($location.search().firstLogin) {
        //console.log('first login');
        $location.search('firstLogin', null);
        if (CONFIG.adwordsID) {
          $window.google_trackConversion({
            google_conversion_id: CONFIG.adwordsID,
            google_conversion_language: "en",
            google_conversion_format: "2",
            google_conversion_color: "ffffff",
            google_conversion_label: "sumICNX9mFYQsNqy2QM",
            google_remarketing_only: false
          });
        }

        if (!$rootScope.formisimoConversionAdded) {
          $('body')
            .append('<script src="//cdn-static.formisimo.com/tracking/js/tracking.js"></script>')
            .append('<script src="//cdn-static.formisimo.com/tracking/js/conversion.js"></script>');

          $rootScope.formisimoConversionAdded = true;
        }
      }

      $location.path('/concierge/carers');
    }
    else {
      $location.path('/error');
    }
  })
  /**
   * Allows for setting dynamic names on form inputs.
   */
  .directive('ngName', function() {
    return {
      priority: 100,
      restrict: 'A',
      require: 'ngModel',
      link: {
        pre: function ngNameLinkFn(scope, elem, attrs, ctrl) {
          ctrl.$name = scope.$eval(attrs.ngName);
          attrs.$set('name', ctrl.$name);
        }
      }
    }
  })
  /**
   * Adds a on/off button group for manipulating a boolean model value.
   */
  .directive('switch', function() {
    return {
      restrict: 'E',
      require: 'ngModel',
      compile: function(element, attrs) {
        var name = attrs['ngModel'];
        var onClass = '{\'btn-primary\': ' + name + ', \'btn-default\': !' + name + '}';
        var onClick = name + ' = true';
        var offClass = '{\'btn-primary\': !' + name + ', \'btn-default\': ' + name + '}';
        var offClick = name + ' = false';
        var htmlText = '<div class="btn-group">' +
                       '  <button type="button" class="btn" ng-class="' + onClass + '" ng-click="' + onClick + '">On</button>' +
                       '  <button type="button" class="btn" ng-class="' + offClass + '" ng-click="' + offClick + '">Off</button>' +
                       '</div>';

        element.replaceWith(htmlText);
      }
    }
  })
  /**
   * Executes an expression when an image finishes loading. Usage:
   *   <img ng-src="{{src}}" image-on-load="imageLoaded()">
   */
  .directive('imageOnLoad', function() {
    return {
      restrict: 'A',
      link: function(scope, element, attrs) {
        if (attrs['imageOnLoad']) {
          element.bind('load', function() {
            scope.$apply(attrs['imageOnLoad']);
          });
        }
      }
    };
  })
  /**
   * Apply the filter truncate logic to the common module.
   */
  .filter('truncate', function() {
    return function(text, length, end) {

      if (text === undefined) {
        return;
      }

      if (isNaN(length)) {
        length = 135;
      }
      if (end === undefined) {
        end = '...';
      }

      if (text.length <= length || text.length - end.length <= length) {
        return text;
      }
      else {
        return String(text).substring(0, length - end.length) + end;
      }

    };
  });
