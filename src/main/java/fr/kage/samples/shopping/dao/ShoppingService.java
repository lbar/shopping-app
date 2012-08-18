package fr.kage.samples.shopping.dao;

import java.util.List;

import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Element;

public class ShoppingService {
	
	private ShoppingDAO shoppingDAO;
	
	public ShoppingDAO getShoppingDAO() {
		return shoppingDAO;
	}

	public void setShoppingDAO(ShoppingDAO shoppingDAO) {
		this.shoppingDAO = shoppingDAO;
	}

	public List<Element> listElements() {
		return shoppingDAO.listElements();
	}
	
	public void addElement(Element element) {
		shoppingDAO.addElement(element);
	}
	
	public void updateElement(long id, Element element) {
		shoppingDAO.updateElement(id, element);
	}
	
	public void deleteElement(long id) {
		shoppingDAO.deleteElement(id);
	}
	
	public List<Category> listCategories() {
		return shoppingDAO.listCategories();
	}
	
	public void addCategory(Category category) {
		shoppingDAO.addCategory(category);
	}
	
	public void updateCategory(long id, Category category) {
		shoppingDAO.updateCategory(id, category);
	}
	
	public void deleteCategory(long id) {
		shoppingDAO.deleteCategory(id);
	}
}
