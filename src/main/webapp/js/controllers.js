"use strict";

angular.module("shopping-app", ["shoppingService"])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
	            .when("/product/list", {templateUrl:"views/product-list.html", controller:MainController})
	            .when("/about", {templateUrl:"views/about.html", controller:AboutController})
	            .otherwise({redirectTo:"/product/list"});
	}])
	.filter("filterProductsInCategory", function() {
		return function(products, category) {
			var filtered = [];
			for (var i = 0; i < products.length; i++) {
				var product = products[i];
				var c = product.category;
				if (c.id == category.id)
					filtered.push(product);
			}
			return filtered;
		}
	});

var MainController = function($scope, $location, $rootElement, Category, Product) {
	$scope.categories = Category.query();
	$scope.products = Product.query();
	$scope.editedProduct = null;
	
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
	
	$scope.enableEditingMode = function(product) {
		if ($scope.editedProduct != null)
			$scope.saveProduct($scope.editedProduct);
		$scope.editedProduct = product;
	};
	
	$scope.saveProduct = function(product) {
		product.$save({id: product.id});
		$scope.editedProduct = null;
	};
	
	$scope.deleteProduct = function(product) {
		if (confirm("The product " + product.name + " will be deleted. Are you sure?"))
			product.$delete({id: product.id}, function() {
				$scope.products = Product.query();
			});
	}
}

var CategoryController = function($scope, Category, Product) {
	$scope.newProduct = null;
	
	$scope.addProductInCategory = function(category) {
		$scope.newProduct.category = category;
		Product.create($scope.newProduct, function() {
			$scope.products = Product.query();
		});
		$scope.newProduct = null;
	};
}

var ProductController = function($scope, Category, Product) {
	
}

var AboutController = function($scope) {
	// nothing to do
}