"use strict";

angular.module("shoppingService", ["ngResource"])
		.factory("Category", function ($resource) {
            return $resource("api/category/:id", {}, {
            	"create": {method: "POST"},
                "save": {method:"PUT"}
            });
        })
        .factory("Product", function ($resource) {
            return $resource("api/product/:id", {}, {
            	"create": {method: "POST"},
                "save": {method:"PUT"}
            });
        });