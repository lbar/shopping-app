"use strict";

angular.module("shopping-app", ["shoppingService"])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
	            .when("/element/list", {templateUrl:"views/element-list.html", controller:ElementController.list})
	            .otherwise({redirectTo:"/element/list"});
	}]);

var ElementController = {
	list: function($scope, $location, Element) {
		$scope.elements = Element.query();
		$scope.newElement = function() {
			$("add-element").show();
		}
		$scope.updateElement = function(element) {
			$location.path("/element/modify");
		}
	},
	
	add: function($scope, $location, Element) {
		
	},
	
	update: function($scope, $location, $routeParams, Element) {
		
	},
	
	delete: function($scope, $location, $routeParams, Element) {
		
	}
	
}