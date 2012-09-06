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
import fr.kage.samples.shopping.model.Element;

@Controller
public class ShoppingController {
	
	@Resource(name="shoppingService")
	private ShoppingService shoppingService;

	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

	@RequestMapping(value="/element", method=GET)
	public @ResponseBody List<Element> listElements() {
		return shoppingService.listElements();
	}
	
	@RequestMapping(value="/element", method=POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addElement(@RequestBody Element element) {
		shoppingService.addElement(element);
	}
	
	@RequestMapping(value="/element/{id}", method=PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editElement(@PathVariable long id, @RequestBody Element element) {
		shoppingService.updateElement(id, element);
	}
	
	@RequestMapping(value="/element/{id}", method=DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteElement(@PathVariable long id) {
		shoppingService.deleteElement(id);
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
