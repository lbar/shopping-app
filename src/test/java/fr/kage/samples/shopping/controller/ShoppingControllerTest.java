package fr.kage.samples.shopping.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import fr.kage.samples.shopping.dao.ShoppingService;
import fr.kage.samples.shopping.model.Element;

public class ShoppingControllerTest {

	private ShoppingController shoppingController;
	private ShoppingService mockedShoppingService;
	
	@Before
	public void setUp() {
		shoppingController = new ShoppingController();
		
		mockedShoppingService = mock(ShoppingService.class);
		shoppingController.setShoppingService(mockedShoppingService);
	}
	
	@Test
	public void testList() {
		{
			doReturn(new ArrayList<Element>()).when(mockedShoppingService).listElements();
			
			List<Element> first = shoppingController.list();
			assertTrue(first.isEmpty());
		}
		{
			doReturn(ImmutableList.of(newElement(1), newElement(2))).when(mockedShoppingService).listElements();
			
			List<Element> second = shoppingController.list();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
			
			assertEquals(1, (long) second.get(0).getId());
			assertEquals(2, (long) second.get(1).getId());
		}
	}
	
	@Test
	public void testAdd() {
		Element added = newElement(1);
		verify(mockedShoppingService, never()).addElement(added);

		shoppingController.add(added);
		verify(mockedShoppingService, times(1)).addElement(added);
	}
	
	@Test
	public void testEdit() {
		Element modified = newElement(1);
		verify(mockedShoppingService, never()).updateElement(1, modified);
		
		shoppingController.edit(1, modified);
		verify(mockedShoppingService, times(1)).updateElement(1, modified);
	}
	
	@Test
	public void testDelete() {
		verify(mockedShoppingService, never()).deleteElement(1);
		
		shoppingController.delete(1);
		verify(mockedShoppingService, times(1)).deleteElement(1);
	}
	
	static Element newElement(long id) {
		Element mocked = mock(Element.class);
		when(mocked.getId()).thenReturn(id);
		return mocked;
	}
}
