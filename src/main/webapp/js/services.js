"use strict";

angular.module("shoppingService", ["ngResource"])
		.factory("Category", function ($resource) {
            return $resource("api/category/:id", {}, {
            	"create": {method: "POST"},
                "save": {method:"PUT"}
            });
        })
        .factory("Element", function ($resource) {
            return $resource("api/element/:id", {}, {
            	"create": {method: "POST"},
                "save": {method:"PUT"}
            });
        });