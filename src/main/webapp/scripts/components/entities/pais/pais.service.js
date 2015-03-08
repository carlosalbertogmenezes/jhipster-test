'use strict';

angular.module('jhipsterApp')
    .factory('Pais', function ($resource) {
        return $resource('api/paiss/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
