'use strict';

angular.module('htConfig', [])
  .constant('CONFIG', {
    env: 'production',
    dateFormat: 'dd-MM-yyyy',
    dateFormatSignup: 'dd/MM/yyyy',
    mediumDateFormat: 'MMM d, y',
    mediumDayFormat: 'MMM d',
    dayOfMonthFormat: 'EEE, MMM d',
    timeFormat: 'HH:mm',
    dateTimeFormat: 'dd-MM-yyyy HH:mm',
    stripePublishable: 'pk_live_pItxNh2O12493abTxesaJbhQ',
    gaID: 'UA-32248330-1',
    adwordsID: '992783664',
    intercom: {
      appID: 'h94aur3l'
    },
    reviewsItemsPerPage: 2,
    contact: {
      phone: '02038087719'
    }
  })
  .constant('ContractStates',
    // keep it in sync to STATE from api/concierge-contract/concierge-contract.model.js
    {
      PENDING: 0,
      REJECTED_BY_CARER: 1,
      REJECTED_BY_CLIENT: 2,
      ACCEPTED: 3,
      ACTIVE: 4,
      DISPUTED_BY_CARER: 5,
      DISPUTED_BY_CLIENT: 6,
      TERMINATED: 7,
      FINISHED: 8
    });
