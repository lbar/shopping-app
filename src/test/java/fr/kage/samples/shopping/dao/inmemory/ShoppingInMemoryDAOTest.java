package fr.kage.samples.shopping.dao.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.kage.samples.shopping.model.Element;

public class ShoppingInMemoryDAOTest {
	
	private ShoppingInMemoryDAO shoppingInMemoryDAO;
	
	@Before
	public void setUp() {
		shoppingInMemoryDAO = new ShoppingInMemoryDAO();
	}

	@Test
	public void testList() {
		{
			List<Element> first = shoppingInMemoryDAO.listElements();
			assertTrue(first.isEmpty());
		}
		{
			shoppingInMemoryDAO.addElement(newElement(1));
			shoppingInMemoryDAO.addElement(newElement(2));
			
			List<Element> second = shoppingInMemoryDAO.listElements();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
		}
	}
	
	@Test
	public void testAdd() {
		assertTrue(shoppingInMemoryDAO.listElements().isEmpty());
		{
			Element added = newElement(1);
			shoppingInMemoryDAO.addElement(added);
			assertEquals(added, shoppingInMemoryDAO.listElements().get(0));
		}
	}
	
	@Test
	public void testEdit() {
		Element added = newElement(1);
		shoppingInMemoryDAO.addElement(added);
		assertEquals(added, shoppingInMemoryDAO.listElements().get(0));
		
		Element modified = newElement(1);
		assertNotSame(modified, added);
		shoppingInMemoryDAO.updateElement(1, modified);
		assertEquals(modified, shoppingInMemoryDAO.listElements().get(0));
	}
	
	@Test
	public void testDelete() {
		Element added = newElement(1);
		shoppingInMemoryDAO.addElement(added);
		assertEquals(added, shoppingInMemoryDAO.listElements().get(0));
		
		shoppingInMemoryDAO.deleteElement(1);
		assertTrue(shoppingInMemoryDAO.listElements().isEmpty());
	}
	
	static Element newElement(long id) {
		Element mocked = mock(Element.class);
		when(mocked.getId()).thenReturn(id);
		return mocked;
	}
}
