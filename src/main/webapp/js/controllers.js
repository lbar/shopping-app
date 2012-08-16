"use strict";

angular.module("shopping-app", ["shoppingService"])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
	            .when("/element/list", {templateUrl:"views/element-list.html", controller:ElementController.list})
	            .otherwise({redirectTo:"/element/list"});
	}]);

var ElementController = {
	list: function($scope, $location, $rootElement, Element) {
		$scope.elements = Element.query();
		$scope.editedElement = null;
		
		$scope.addElement = function() {
			Element.create($scope.newElement, function() {
				$scope.elements = Element.query();
			});
			$scope.newElement = null;
		};
		
		$scope.enableEditingMode = function(element) {
			if ($scope.editedElement != null)
				$scope.saveElement($scope.editedElement);
			$scope.editedElement = element;
		};
		
		$scope.saveElement = function(element) {
			element.$save({id: element.id});
			$scope.editedElement = null;
		};
		
		$scope.deleteElement = function(element) {
			if (confirm("The element " + element.name + " will be deleted. Are you sure?"))
				element.$delete({id: element.id}, function() {
					$scope.elements = Element.query();
				});
		}
	},
	
	add: function($scope, $location, Element) {
		
	},
	
	update: function($scope, $location, $routeParams, Element) {
		
	},
	
	delete: function($scope, $location, $routeParams, Element) {
		
	}
	
}