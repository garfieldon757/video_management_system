'use strict';

/**
 * Utility class extracting and navigating through the weeks of a contract schedule for client dashboard.
 */
angular.module('scheduleWeek', [])
  .service('ScheduleWeek', function(DAYS) {

    function ScheduleWeek(contract) {
      this.contract = contract;
      this.week = 1;
      this.days = [];

      this.update();
    }

    ScheduleWeek.prototype.updateDates = function() {
      this.start = moment(this.contract.start);
      this.end = this.contract.end ? moment(this.contract.end) : null;
      this.length = this.end ? Math.ceil((this.end.diff(this.start, 'days') + 1) / 7) : 0;

      if (this.length)
        this.week = Math.min(this.week, this.length);
    };

    ScheduleWeek.prototype.update = function() {
      this.updateDates();

      var date = moment(this.start).add(this.week - 1, 'weeks');
      var end = this.end ? moment(this.end) : moment(date).add(100, 'years');

      this.days.length = 0;

      while (!date.isAfter(end, 'days') && this.days.length < 7) {
        this.days.push({
          day: DAYS[date.isoWeekday() - 1],
          date: moment(date).toDate()
        });

        date.add(1, 'days');
      }

      return this.days;
    };

    ScheduleWeek.prototype.hasPrev = function() {
      return this.week > 1;
    };

    ScheduleWeek.prototype.hasNext = function() {
      return !this.length || this.week < this.length;
    };

    ScheduleWeek.prototype.prev = function() {
      if (this.hasPrev()) {
        this.week--;
        this.update();
      }

      return this.days;
    };

    ScheduleWeek.prototype.next = function() {
      if (this.hasNext()) {
        this.week++;
        this.update();
      }

      return this.days;
    };

    return ScheduleWeek;
  });