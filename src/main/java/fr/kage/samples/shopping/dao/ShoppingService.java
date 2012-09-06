package fr.kage.samples.shopping.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Product;

@Service("shoppingService")
public class ShoppingService {
	
	@Resource(name="shoppingDAO")
	private ShoppingDAO shoppingDAO;
	
	public List<Product> listProducts() {
		return shoppingDAO.listProducts();
	}
	
	public void addProduct(Product product) {
		shoppingDAO.addProduct(product);
	}
	
	public void updateProduct(long id, Product product) {
		shoppingDAO.updateProduct(id, product);
	}
	
	public void deleteProduct(long id) {
		shoppingDAO.deleteProduct(id);
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
