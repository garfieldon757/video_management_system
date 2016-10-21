'use strict';

/**
 * Utility class for using the UI Bootstrap pagination component.
 */
angular.module('pagination', [])
  .service('Pagination', function() {

    function Pagination(itemsPerPage, totalItems, currentPage) {
      var _itemsPerPage = itemsPerPage || 50;
      var _totalItems = totalItems || 0;
      var _currentPage = currentPage || 1;
      var _limit = 0;
      var _count = 0;

      Object.defineProperty(this, "totalItems", {
        get: function() {
          return _totalItems;
        },
        set: function(value) {
          _totalItems = value;
          this.update();
        }.bind(this)
      });

      Object.defineProperty(this, "currentPage", {
        get: function() {
          return _currentPage;
        },
        set: function(value) {
          _currentPage = value;
          this.update();
        }.bind(this)
      });

      Object.defineProperty(this, "itemsPerPage", {
        get: function() {
          return _itemsPerPage;
        },
        set: function(value) {
          _itemsPerPage = value;
          this.update();
        }.bind(this)
      });

      Object.defineProperty(this, "limit", {
        get: function() {
          return _limit;
        }
      });

      Object.defineProperty(this, "count", {
        get: function() {
          return _count;
        }
      });

      this._update = function() {
        _limit = Math.min(this.totalItems, this.currentPage * this.itemsPerPage);
        _count = Math.min(this.itemsPerPage, this.totalItems - ((this.currentPage - 1) * this.itemsPerPage));
      };

      this._update();
    }

    Pagination.prototype.update = function() {
      this._update();
    };

    return Pagination;
  });
