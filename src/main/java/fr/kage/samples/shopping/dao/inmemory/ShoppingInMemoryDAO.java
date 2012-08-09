package fr.kage.samples.shopping.dao.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import fr.kage.samples.shopping.dao.ShoppingDAO;
import fr.kage.samples.shopping.dao.ShoppingDAOException;
import fr.kage.samples.shopping.model.Element;

public class ShoppingInMemoryDAO implements ShoppingDAO {
	
	private ConcurrentHashMap<Long, Element> elements = new ConcurrentHashMap<>();
	
	@Override
	public List<Element> listElements() {
		return new ArrayList<>(elements.values());
	}

	@Override
	public void addElement(Element element) { // FIXME auto-generation of id
		if (elements.containsKey(element.getId()))
			throw new ShoppingDAOException("Error: try to update a non-existent element with id <" + element.getId() + ">");
		
		elements.put(element.getId(), element);
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

}
