"use strict";

angular.module("shoppingService", ["ngResource"])
        .factory('Element', function ($resource) {
            return $resource("api/element/:id", {}, {
            	"create": {method: "POST"},
                "save": {method:"PUT"}
            });
        });