'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('estado', {
                parent: 'entity',
                url: '/estado',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'jhipsterApp.estado.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estado/estados.html',
                        controller: 'EstadoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estado');
                        return $translate.refresh();
                    }]
                }
            })
            .state('estadoDetail', {
                parent: 'entity',
                url: '/estado/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'jhipsterApp.estado.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estado/estado-detail.html',
                        controller: 'EstadoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estado');
                        return $translate.refresh();
                    }]
                }
            });
    });
