package fr.kage.samples.shopping.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.kage.samples.shopping.dao.ShoppingService;
import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Product;

@Controller
public class ShoppingController {
	
	@Resource(name="shoppingService")
	private ShoppingService shoppingService;

	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

	@RequestMapping(value="/product", method=GET)
	public @ResponseBody List<Product> listProducts() {
		return shoppingService.listProducts();
	}
	
	@RequestMapping(value="/product", method=POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addProduct(@RequestBody Product product) {
		shoppingService.addProduct(product);
	}
	
	@RequestMapping(value="/product/{id}", method=PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editProduct(@PathVariable long id, @RequestBody Product product) {
		shoppingService.updateProduct(id, product);
	}
	
	@RequestMapping(value="/product/{id}", method=DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable long id) {
		shoppingService.deleteProduct(id);
	}
	
	@RequestMapping(value="/category", method=GET)
	public @ResponseBody List<Category> listCategories() {
		return shoppingService.listCategories();
	}
	
	@RequestMapping(value="/category", method=POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addCategory(@RequestBody Category category) {
		shoppingService.addCategory(category);
	}
	
	@RequestMapping(value="/category/{id}", method=PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editCategory(@PathVariable long id, @RequestBody Category category) {
		shoppingService.updateCategory(id, category);
	}
	
	@RequestMapping(value="/category/{id}", method=DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable long id) {
		shoppingService.deleteCategory(id);
	}
	
}
