'use strict';

angular.module('jhipsterApp')
    .factory('Estado', function ($resource) {
        return $resource('api/estados/:id', {}, {
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
