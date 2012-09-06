package fr.kage.samples.shopping.dao.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import fr.kage.samples.shopping.dao.ShoppingDAO;
import fr.kage.samples.shopping.dao.ShoppingDAOException;
import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Product;

public class ShoppingInMemoryDAO implements ShoppingDAO {
	
	private ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Long, Category> categories = new ConcurrentHashMap<>();
	
	@Override
	public List<Product> listProducts() {
		return new ArrayList<>(products.values());
	}

	@Override
	public void addProduct(Product product) {
		if (product.getId() != null)
			throw new ShoppingDAOException("Error: try to insert a product with id <" + product.getId() + ">");
		
		product.setId(nextProductId());
		products.put(product.getId(), product);
	}

	private Long nextProductId() {
		long max = 0;
		for (Long currentId : products.keySet())
			max = Math.max(max, currentId);
		return max + 1;
	}

	@Override
	public void updateProduct(long id, Product product) {
		if (!products.containsKey(id))
			throw new ShoppingDAOException("Error: try to update a non-existent product with id <" + id + ">");
		
		products.put(id, product);
	}

	@Override
	public void deleteProduct(long id) {
		if (!products.containsKey(id))
			throw new ShoppingDAOException("Error: try to delete a non-existent product with id <" + id + ">");
		
		products.remove(id);
	}

	@Override
	public List<Category> listCategories() {
		return new ArrayList<>(categories.values());
	}

	@Override
	public void addCategory(Category category) {
		if (category.getId() != null)
			throw new ShoppingDAOException("Error: try to insert a category with id <" + category.getId() + ">");
		
		category.setId(nextCategoryId());
		categories.put(category.getId(), category);
	}

	private Long nextCategoryId() {
		long max = 0;
		for (Long currentId : categories.keySet())
			max = Math.max(max, currentId);
		return max + 1;
	}

	@Override
	public void updateCategory(long id, Category category) {
		if (!categories.containsKey(id))
			throw new ShoppingDAOException("Error: try to update a non-existent category with id <" + id + ">");
		
		categories.put(id, category);
	}

	@Override
	public void deleteCategory(long id) {
		if (!categories.containsKey(id))
			throw new ShoppingDAOException("Error: try to delete a non-existent category with id <" + id + ">");
		
		categories.remove(id);
	}

}
