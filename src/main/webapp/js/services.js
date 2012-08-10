"use strict";

angular.module("shoppingService", ["ngResource"])
        .factory('Element', function ($resource) {
            return $resource('api/element/:id', {}, {
                'save': {method:'PUT'}
            });
        });