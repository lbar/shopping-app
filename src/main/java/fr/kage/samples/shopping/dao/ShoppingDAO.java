package fr.kage.samples.shopping.dao;

import java.util.List;

import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Product;

public interface ShoppingDAO {

	List<Product> listProducts();
	
	void addProduct(Product product);
	
	void updateProduct(long id, Product product);
	
	void deleteProduct(long id);
	
	List<Category> listCategories();
	
	void addCategory(Category category);
	
	void updateCategory(long id, Category category);
	
	void deleteCategory(long id);
	
}
