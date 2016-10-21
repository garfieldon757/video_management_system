'use strict';

angular.module('htModels', [])
  /**
   * Session
   */
  .factory('Session', function($resource) {
    return $resource('/api/session', {}, {
      logout: {
        method: 'DELETE'
      }
    });
  })
  /**
   * Interests
   */
  .factory('Interest', function($resource) {
    return $resource('/api/interests/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Invites
   */
  .factory('Invite', function($resource) {
    return $resource('/api/invites/:id/:action', {
      id: '@id', action: '@action'
    }, {
      accept: {
        method: 'POST',
        params: {action: 'accept'}
      }
    });
  })
  /**
   * Medical conditions
   */
  .factory('MedicalCondition', function($resource) {
    return $resource('/api/medical-conditions/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Message
   */
  .factory('Message', function($resource) {
    return $resource('/api/users/:user_id/messages/:message_id', {
      user_id: '@user_id', message_id: '@message_id'
    }, {
      inbox: {
        method: 'GET',
        params: {message_id: 'inbox'}
      },
      outbox: {
        method: 'GET',
        params: {message_id: 'outbox'}
      },
      update: {
        method: 'PUT'
      },
      remove: {
        method: 'DELETE'
      }
    });
  })
  /**
   * Contact
   */
  .factory('Contact', function($resource) {
    return $resource('/api/users/:user_id/contacts/:action', {
      user_id: '@user_id', action: '@action'
    }, {
      invite: {
        method: 'POST',
        params: {action: 'invite'}
      }
    });
  })
  /**
   * Support needs
   */
  .factory('SupportNeed', function($resource) {
    return $resource('/api/support-needs/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Support user
   */
  .factory('SupportUser', function($resource) {
    return $resource('/api/support-user', {}, {});
  })
  /**
   * Concierge Carers
   */
  .factory('CarerUser', function($resource) {
    return $resource('/api/concierge/carers/:id', {
      id: '@_id'
    }, {
      paginate: {
        method: 'GET',
        params: {
          page: '@page',
          limit: '@limit'
        }
      }
    });
  })
  /**
   * Users
   */
  .factory('User', function($resource) {
    return $resource('/api/users/:id/:action', {
      id: '@_id', action: '@action'
    }, {
      update: {
        method: 'PUT'
      },
      deleteUnapprovedProfile: {
        method: 'DELETE',
        params: {
          action: 'unapproved-profile'
        }
      },
      uploadPicture: {
        method: 'POST',
        params: {
          action: 'picture'
        }
      },
      removePicture: {
        method: 'DELETE',
        params: {
          action: 'picture'
        }
      },
      hasUnapprovedPicture: {
        method: 'HEAD',
        params: {
          action: 'unapproved-picture'
        }
      },
      approvePicture: {
        method: 'PUT',
        params: {
          action: 'unapproved-picture'
        }
      },
      removeUnapprovedPicture: {
        method: 'DELETE',
        params: {
          action: 'unapproved-picture'
        }
      },
      paginate: {
        method: 'GET',
        params: {
          page: '@page',
          limit: '@limit'
        }
      }
    });
  })
  /**
   * Coupons
   */
  .factory('Coupon', function($resource) {
    return $resource('/api/coupons/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Register
   */
  .factory('Register', function($resource) {
    return $resource('/api/register', {}, {});
  })
  /**
   * Password reset
   */
  .factory('PasswordReset', function($resource) {
    return $resource('/api/password-reset/:id', {
      id: '@_id'
    }, {
      reset: {
        method: 'PUT'
      }
    });
  })
  /**
   * Concierge care services
   */
  .factory('ConciergeCareService', function($resource) {
    return $resource('/api/concierge/care-services/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Concierge spoken languages
   */
  .factory('ConciergeSpokenLanguage', function($resource) {
    return $resource('/api/concierge/spoken-languages/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Concierge locations
   */
  .factory('ConciergeLocations', function($resource) {
    return $resource('/api/concierge/carers/locations', {}, {});
  })
  /**
   * Concierge contracts
   */
  .factory('ConciergeContracts', function($resource) {
    return $resource('/api/concierge/contracts/:id/:action/:client_id', {
      id: '@_id'
    }, {
      detailedView: {
        method: 'GET',
        params: {
          action: 'detailedView',
          status: '@status'
        }
      },
      update: {
        method: 'PUT'
      },
      accept: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'accept'
        }
      },
      end: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'end'
        }
      },
      terminate: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'terminate'
        }
      },
      reactivate: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'reactivate'
        }
      },
      last: {
        method: 'GET',
        params: {
          action: 'last',
          client_id: '@client_id'
        }
      },
      paginate: {
        method: 'GET',
        params: {
          page: '@page',
          limit: '@limit'
        }
      },
      reject: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'reject'
        }
      },
      dispute: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'dispute'
        }
      }
    });
  })
  /**
   * Concierge contract timesheets
   */
  .factory('ConciergeContractsTimesheets', ['$resource', function($resource) {
    return {
      getTimesheetForContractWeek: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number', {c_contract_id: '@c_contract_id', week_number: '@week_number'}),

      addTimesheetForContractWeek: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number/add', {c_contract_id: '@c_contract_id', week_number: '@week_number'}, {put: {method:'put'}}),

      changeTimesheetSessionForContractWeek: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number/:session_id/change',
        {c_contract_id: '@c_contract_id', week_number: '@week_number', session_id: '@session_id'}, {put: {method:'put'}}),

      cancelTimesheetSessionForContractWeek: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number/:session_id/cancel',
        {c_contract_id: '@c_contract_id', week_number: '@week_number', session_id: '@session_id'}, {put: {method:'put'}}),

      uncancelTimesheetSessionForContractWeek: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number/:session_id/uncancel',
        {c_contract_id: '@c_contract_id', week_number: '@week_number', session_id: '@session_id'}, {put: {method:'put'}}),

      manuallyPayTimesheet: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number/manual_pay',
        {c_contract_id: '@c_contract_id', week_number: '@week_number'}, {put: {method:'put'}}),

      payTimesheet: $resource('/api/concierge/contracts/timesheets/:c_contract_id/:week_number/pay',
        {c_contract_id: '@c_contract_id', week_number: '@week_number'}, {put: {method:'put'}})
    };
  }])
  /**
   * Concierge payments
   */
  .factory('ConciergePayments', function($resource) {
    return $resource('/api/concierge/payments/:id/:action', {
      id: '@_id'
    }, {
      charge: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'charge'
        }
      },
      cancel: {
        method: 'PUT',
        params: {
          id: '@_id',
          action: 'cancel'
        }
      }
    });
  })
  /**
   * Account
   */
   .factory('Account', ['$resource', function($resource) {
     return {
       getAccountByUserId: $resource('/api/account/:user_id',
         {user_id: '@user_id'},
         {get: {method: 'get'}}),
       createAccountWithStripeToken: $resource('/api/account/:user_id',
         {user_id: '@user_id'},
         {save: {method: 'post'}})
     }
   }])
  /**
   * Concierge visits
   */
  .factory('ConciergeVisits', function($resource) {
    return $resource('/api/concierge/visits/:id', {
      id: '@_id'
    }, {});
  })
  /**
   * Concierge visits summary
   */
  .factory('ConciergeVisitsSummary', function($resource) {
    return $resource('/api/concierge/visits/summary', {}, {});
  })
  /**
   * Concierge happiness states
   */
  .factory('ConciergeHappinessState', function($resource) {
    return $resource('/api/concierge/happiness-states/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Concierge messages
   */
  .factory('ConciergeMessages', function($resource) {
    return $resource('/api/concierge/messages/:id/:action', {
      id: '@_id'
    }, {
      query: {
        method: 'GET',
        params: {
          page: '@page',
          limit: '@limit'
        }
      },
      inbox: {
        method: 'GET',
        params: {
          action: 'inbox',
          page: '@page',
          limit: '@limit'
        }
      },
      sent: {
        method: 'GET',
        params: {
          action: 'sent',
          page: '@page',
          limit: '@limit'
        }
      },
      setSpam: {
        method: 'PUT',
        params: {
          action: 'spam'
        }
      },
      setNotSpam: {
        method: 'PUT',
        params: {
          action: 'not-spam'
        }
      }
    });
  })
  /**
   * Reviews
   */
  .factory('Reviews', function($resource) {
    return $resource('/api/reviews/:id', {
      id: '@_id'
    }, {
      update: {
        method: 'PUT'
      },
      paginate: {
        method: 'GET',
        params: {}
      }
    });
  })
  /**
   * Config
   */
  .factory('Config', function($resource) {
    return $resource('/api/config', {}, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Website config
   */
  .factory('WebsiteConfig', function($resource) {
    return $resource('/api/website-config', {}, {
      update: {
        method: 'PUT'
      }
    });
  })
  /**
   * Signup sources
   */
  .factory('SignupSources', function($resource) {
    return $resource('/api/signup-sources', {}, {
      paginate: {
        method: 'GET',
        params: {}
      }
    });
  })
  /**
   * Logs
   */
  .factory('Logs', function($resource) {
    return $resource('/api/logs', {}, {
      paginate: {
        method: 'GET',
        params: {}
      }
    });
  });
