'use strict';

angular.module('intercomCommunications', [])
  .factory('IntercomCommunications', function ($resource) {
    return $resource('/api/intercom/:id/:action', {
        id: '@id', action: '@action'
      },
      {
        createLead: {
          url: '/api/intercom/createLead',
          method: 'POST'
        },
        /*
          {id: ...},
          {
            email: ...
            custom_attributes: {
              attr1: ...,
              attr2: ...,
              ...
            }
          }
        */
        updateUser: {
          method: 'PUT',
          params: {
            action: 'user'
          }
        },
        /*
          {id: ...}
        */
        deleteUser: {
          method: 'DELETE',
          params: {
            action: 'user'
          }
        },
        /*
          {id: ...},
          {
            event_name: ...,
            metadata?: {
              attr1: ..., // MAX 5 attrs
              attr2: ...,
              ...
            }
         */
        sendEvent: {
          method: 'PUT',
          params: {
            action: 'event'
          }
        }
      });
  });
