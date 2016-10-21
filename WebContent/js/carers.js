'use strict';

angular.module('htApp')
  .controller('ConciergeCarersCtrl', function(Alerts, CarerUser) {
    var conciergeCarersCtrl = this;
    var data = {};
    var query = {};

    angular.extend(conciergeCarersCtrl, {
      searchQuery: '',
      itemCount: 0,
      submitted: false,
      filtered: false,

      searchForCarers: function() {
        query = {
          page: 1,
          limit: 9,
          q: conciergeCarersCtrl.searchQuery,
          getFilterData: true
        };

        conciergeCarersCtrl.submitted = false;

        CarerUser.paginate(query).$promise
          .then(function(results) {
            data = results;
            conciergeCarersCtrl.carers = results.users;
            conciergeCarersCtrl.itemCount = results.itemCount;
            conciergeCarersCtrl.skills = results.filterSkills;
            conciergeCarersCtrl.languages = results.filterLanguages;
            conciergeCarersCtrl.conditions = results.filterConditions;
            conciergeCarersCtrl.refineSearch = false;
            conciergeCarersCtrl.submitted = true;
            conciergeCarersCtrl.filtered = false;
            conciergeCarersCtrl.clearFilters(false);
          })
          .catch(function(err) {
            console.error(err);
            Alerts.error('Error loading data.');
          });
      },

      filterResults: function() {
        query = {
          page: 1,
          limit: 9,
          q: conciergeCarersCtrl.searchQuery,
          skills: conciergeCarersCtrl.selectedSkill ? conciergeCarersCtrl.selectedSkill.value : undefined,
          languages: conciergeCarersCtrl.selectedLanguage ? conciergeCarersCtrl.selectedLanguage.value : undefined,
          conditions: conciergeCarersCtrl.selectedCondition ? conciergeCarersCtrl.selectedCondition.value : undefined
        };

        conciergeCarersCtrl.submitted = false;

        CarerUser.paginate(query).$promise
          .then(function(results) {
            data = results;
            conciergeCarersCtrl.carers = results.users;
            conciergeCarersCtrl.itemCount = results.itemCount;
            conciergeCarersCtrl.submitted = true;
            conciergeCarersCtrl.filtered = !!(query.skills || query.languages || query.conditions);
          })
          .catch(function(err) {
            console.error(err);
            Alerts.error('Error loading data.');
          });
      },

      loadMore: function() {
        if (this.hasMore()) {
          query.page++;

          CarerUser.paginate(query).$promise
            .then(function(results) {
              data = results;
              conciergeCarersCtrl.carers = conciergeCarersCtrl.carers.concat(results.users);
            })
            .catch(function(err) {
              console.error(err);
              Alerts.error('Error loading data.');
            });
        }
      },

      clearFilters: function(reload) {
        conciergeCarersCtrl.selectedSkill = null;
        conciergeCarersCtrl.selectedLanguage = null;
        conciergeCarersCtrl.selectedCondition = null;

        if (reload)
          this.filterResults();
      },

      hasMore: function() {
        return data.page < data.pageCount;
      }
    });
  });
