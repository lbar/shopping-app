package fr.kage.samples.shopping.dao.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import fr.kage.samples.shopping.dao.ShoppingDAO;
import fr.kage.samples.shopping.dao.ShoppingDAOException;
import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Element;

public class ShoppingInMemoryDAO implements ShoppingDAO {
	
	private ConcurrentHashMap<Long, Element> elements = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Long, Category> categories = new ConcurrentHashMap<>();
	
	@Override
	public List<Element> listElements() {
		return new ArrayList<>(elements.values());
	}

	@Override
	public void addElement(Element element) {
		if (element.getId() != null)
			throw new ShoppingDAOException("Error: try to insert a element with id <" + element.getId() + ">");
		
		element.setId(nextElementId());
		elements.put(element.getId(), element);
	}

	private Long nextElementId() {
		long max = 0;
		for (Long currentId : elements.keySet())
			max = Math.max(max, currentId);
		return max + 1;
	}

	@Override
	public void updateElement(long id, Element element) {
		if (!elements.containsKey(id))
			throw new ShoppingDAOException("Error: try to update a non-existent element with id <" + id + ">");
		
		elements.put(id, element);
	}

	@Override
	public void deleteElement(long id) {
		if (!elements.containsKey(id))
			throw new ShoppingDAOException("Error: try to delete a non-existent element with id <" + id + ">");
		
		elements.remove(id);
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
