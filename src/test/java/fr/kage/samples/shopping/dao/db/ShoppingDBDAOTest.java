package fr.kage.samples.shopping.dao.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Element;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ShoppingDBDAOTest {

	private ShoppingDBDAO shoppingDBDAO;
	
	@Autowired
	public void setShoppingDAO(ShoppingDBDAO shoppingDAO) {
		shoppingDBDAO = shoppingDAO;
	}
	
	@Before
	public void clean() {
		shoppingDBDAO.cleanAllEntities();
	}
	
	@Test
	public void testListElements() {
		{
			List<Element> first = shoppingDBDAO.listElements();
			assertTrue(first.isEmpty());
		}
		{
			shoppingDBDAO.addElement(newElement("product1", 2));
			shoppingDBDAO.addElement(newElement("product2", 5));
			
			List<Element> second = shoppingDBDAO.listElements();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
		}
	}
	
	@Test
	public void testAddElement() {
		assertTrue(shoppingDBDAO.listElements().isEmpty());
		{
			Element added = newElement("product", 3);
			shoppingDBDAO.addElement(added);
			assertEquals(added, shoppingDBDAO.listElements().get(0));
		}
	}
	
	@Test
	public void testEditElement() {
		Element added = newElement("product1", 4);
		shoppingDBDAO.addElement(added);
		assertEquals(added, shoppingDBDAO.listElements().get(0));
		
		Element modified = newElement("product2", 2);
		modified.setId(added.getId());
		assertNotSame(modified, added);
		shoppingDBDAO.updateElement(added.getId(), modified);
		assertEquals(modified, shoppingDBDAO.listElements().get(0));
	}
	
	@Test
	public void testDeleteElement() {
		Element added = newElement("product1", 4);
		shoppingDBDAO.addElement(added);
		assertEquals(added, shoppingDBDAO.listElements().get(0));
		
		System.out.println("Delete element with id: " + added.getId());
		shoppingDBDAO.deleteElement(added.getId());
		{
			List<Element> allElements = shoppingDBDAO.listElements();
			assertTrue(allElements.isEmpty());
		}
	}
	
	static Element newElement(String name, int amount) {
		final Element mocked = new Element();
		mocked.setName(name);
		mocked.setAmount(amount);
		return mocked;
	}
	
	@Test
	public void testListCategories() {
		{
			List<Category> first = shoppingDBDAO.listCategories();
			assertTrue(first.isEmpty());
		}
		{
			shoppingDBDAO.addCategory(newCategory("product1"));
			shoppingDBDAO.addCategory(newCategory("product2"));
			
			List<Category> second = shoppingDBDAO.listCategories();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
		}
	}
	
	@Test
	public void testAddCategory() {
		assertTrue(shoppingDBDAO.listCategories().isEmpty());
		{
			Category added = newCategory("product");
			shoppingDBDAO.addCategory(added);
			assertEquals(added, shoppingDBDAO.listCategories().get(0));
		}
	}
	
	@Test
	public void testEditCategory() {
		Category added = newCategory("product1");
		shoppingDBDAO.addCategory(added);
		assertEquals(added, shoppingDBDAO.listCategories().get(0));
		
		Category modified = newCategory("product2");
		modified.setId(added.getId());
		assertNotSame(modified, added);
		shoppingDBDAO.updateCategory(added.getId(), modified);
		assertEquals(modified, shoppingDBDAO.listCategories().get(0));
	}
	
	@Test
	public void testDeleteCategory() {
		Category added = newCategory("product1");
		shoppingDBDAO.addCategory(added);
		assertEquals(added, shoppingDBDAO.listCategories().get(0));
		
		System.out.println("Delete category with id: " + added.getId());
		shoppingDBDAO.deleteCategory(added.getId());
		{
			List<Category> allCategories = shoppingDBDAO.listCategories();
			assertTrue(allCategories.isEmpty());
		}
	}
	
	static Category newCategory(String name) {
		final Category mocked = new Category();
		mocked.setName(name);
		return mocked;
	}
}
