'use strict';

angular.module('htApp')
  .controller('SettingsProfileCtrl', function($scope, $rootScope, Upload, Utils, User, Alerts, IntercomCommunications, Auth) {
    $scope.errors = {};
    var id = $rootScope.currentUser._id;
    var info = $scope.info = {
      viewState: 'loading'
    };

    $scope.pictureUrl = function() {
      return '/api/users/' + id + '/picture'
    };

    $scope.save = function(form) {
      $scope.submitted = true;

      if (form.$valid) {
        User.update({id: id}, info.model).$promise
          .then(function(data) {

            $rootScope.currentUser.name = Utils.fullName(data.details.firstname, data.details.lastname);
            $rootScope.currentUser.username = data.username;

            var intercomUserData = {
                //name: [data.details.firstname, data.details.lastname].join(' '),
                email: data.details.email,
            };
            if(Auth.isCarer()) {
              intercomUserData.custom_attributes = {user_type: 'client'};
            }
            IntercomCommunications.updateUser({
              id: data._id
            }, intercomUserData).$promise
              .then(function() {
                IntercomCommunications.sendEvent({
                  id: data._id
                }, {
                  event_name: 'user-saved',
                  metadata: {
                    admin: Auth.getUsername()
                  }
                });
              });

            if ($scope.info.file) {
              $scope.progress = 0;
              $scope.upload = Upload
                .upload({
                  url: '/api/users/' + id + '/picture',
                  method: 'POST',
                  file: $scope.info.file
                })
                .progress(function(evt) {
                  $scope.progress = parseInt(100.0 * evt.loaded / evt.total);
                })
                .success(function() {
                  $scope.info.file = undefined;
                  info.model.meta.hasPicture = true;
                  $rootScope.currentUser.pictureTS = Date.now();
                  Alerts.success('Profile saved.');
                })
                .error(function(data, status) {
                  Alerts.warning('Profile saved but photo not uploaded.');
                  if (status) // status = 0 when user cancels upload
                  {
                    form['file'].$setValidity('remote', false);
                    $scope.errors['file'] = data['file'] ? data['file'].message : 'failed to upload file';
                  }
                })
                .finally(function() {
                  $scope.upload = null;
                });
            }
            else
              Alerts.success('Profile saved.');
          })
          .catch(function(err) {
            if (err.status == 400) {
              for (var prop in err.data) {
                if (err.data[prop].message) {
                  var msg = err.data[prop].message;
                  if (msg) {
                    prop = prop.replace(/\./g, '_');
                    form[prop].$setValidity('remote', false);
                    $scope.errors[prop] = msg;
                  }
                }
              }
            }
            else
              Alerts.error('Operation failed.');
          });
      }
    };

    $scope.removePicture = function() {
      if (confirm('Delete profile photo?')) {
        User.removePicture({id: info.model._id}).$promise
          .then(function() {
            Alerts.success('Photo deleted');
            info.model.meta.hasPicture = false;
            $rootScope.currentUser.pictureTS = Date.now();
          })
          .catch(function(err) {
            Alerts.error(err);
          });
      }
    };

    User.get({id: id}).$promise
      .then(function(data) {
        info.model = data;
        info.viewState = 'form';
      })
      .catch(function() {
        Alerts.error('Failed to fetch data');
      });
  })
  .controller('SettingsChangePasswordCtrl', function($scope, $rootScope, $log, User, Alerts) {
    var id = $rootScope.currentUser._id;
    var info = $scope.info = {
      model: {}
    };

    $scope.save = function(form) {
      $scope.submitted = true;

      if (form.$valid) {
        User.update({id: id}, {password: info.model.password}).$promise
          .then(function() {
            Alerts.success('Password changed successfully.');

            info.model.password = '';
            info.model.pw_check = '';
            $scope.submitted = false;
          })
          .catch(function(err) {
            $log.error(err);
            Alerts.error('Operation failed.');
          });
      }
    };
  });
