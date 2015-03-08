'use strict';

angular.module('jhipsterApp')
    .controller('EstadoDetailController', function ($scope, $stateParams, Estado, Pais) {
        $scope.estado = {};
        $scope.load = function (id) {
            Estado.get({id: id}, function(result) {
              $scope.estado = result;
            });
        };
        $scope.load($stateParams.id);
    });
