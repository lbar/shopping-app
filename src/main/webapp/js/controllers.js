"use strict";

angular.module("shopping-app", ["shoppingService"])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
	            .when("/element/list", {templateUrl:"views/element-list.html", controller:MainController})
	            .otherwise({redirectTo:"/element/list"});
	}])
	.filter("filterElementsInCategory", function() {
		return function(elements, category) {
			var filtered = [];
			for (var i = 0; i < elements.length; i++) {
				var element = elements[i];
				var c = element.category;
				if (c.id == category.id)
					filtered.push(element);
			}
			return filtered;
		}
	});

var MainController = function($scope, $location, $rootElement, Category, Element) {
	$scope.categories = Category.query();
	$scope.elements = Element.query();
	$scope.editedElement = null;
	
	$scope.addCategory = function() {
		Category.create($scope.newCategory, function() {
			$scope.categories = Category.query();
		});
		$scope.newCategory = null;
	};
	
	$scope.deleteCategory = function(category) {
		if (confirm("The category " + category.name + " will be deleted. Are you sure?"))
			category.$delete({id: category.id}, function() {
				$scope.categories = Category.query();
			});
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
}

var CategoryController = function($scope, Category, Element) {
	$scope.newElement = null;
	
	$scope.addElementInCategory = function(category) {
		$scope.newElement.category = category;
		Element.create($scope.newElement, function() {
			$scope.elements = Element.query();
		});
		$scope.newElement = null;
	};
}

var ElementController = function($scope, Category, Element) {
	
}