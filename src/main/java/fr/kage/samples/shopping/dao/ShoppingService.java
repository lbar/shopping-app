package fr.kage.samples.shopping.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Element;

@Service("shoppingService")
public class ShoppingService {
	
	@Resource(name="shoppingDAO")
	private ShoppingDAO shoppingDAO;
	
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
