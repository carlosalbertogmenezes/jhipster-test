'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('pais', {
                parent: 'entity',
                url: '/pais',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'jhipsterApp.pais.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pais/paiss.html',
                        controller: 'PaisController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pais');
                        return $translate.refresh();
                    }]
                }
            })
            .state('paisDetail', {
                parent: 'entity',
                url: '/pais/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'jhipsterApp.pais.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pais/pais-detail.html',
                        controller: 'PaisDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pais');
                        return $translate.refresh();
                    }]
                }
            });
    });
