'use strict';

angular.module('jhipsterApp')
    .controller('PaisDetailController', function ($scope, $stateParams, Pais, Estado) {
        $scope.pais = {};
        $scope.load = function (id) {
            Pais.get({id: id}, function(result) {
              $scope.pais = result;
            });
        };
        $scope.load($stateParams.id);
    });
