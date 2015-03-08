'use strict';

angular.module('jhipsterApp')
    .controller('PaisController', function ($scope, Pais, Estado) {
        $scope.paiss = [];
        $scope.estados = Estado.query();
        $scope.loadAll = function() {
            Pais.query(function(result) {
               $scope.paiss = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Pais.save($scope.pais,
                function () {
                    $scope.loadAll();
                    $('#savePaisModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Pais.get({id: id}, function(result) {
                $scope.pais = result;
                $('#savePaisModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Pais.get({id: id}, function(result) {
                $scope.pais = result;
                $('#deletePaisConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Pais.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePaisConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.pais = {nome: null, id: null};
        };
    });
