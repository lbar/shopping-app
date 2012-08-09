package fr.kage.samples.shopping.dao;

import java.util.List;

import fr.kage.samples.shopping.model.Element;

public interface ShoppingDAO {

	List<Element> listElements();
	
	void addElement(Element element);
	
	void updateElement(long id, Element element);
	
	void deleteElement(long id);
	
}
