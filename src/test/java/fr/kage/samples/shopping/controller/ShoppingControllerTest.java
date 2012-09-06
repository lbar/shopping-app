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
import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Product;

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
	public void testListProducts() {
		{
			doReturn(new ArrayList<Product>()).when(mockedShoppingService).listProducts();
			
			List<Product> first = shoppingController.listProducts();
			assertTrue(first.isEmpty());
		}
		{
			doReturn(ImmutableList.of(newProduct(1), newProduct(2))).when(mockedShoppingService).listProducts();
			
			List<Product> second = shoppingController.listProducts();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
			
			assertEquals(1, (long) second.get(0).getId());
			assertEquals(2, (long) second.get(1).getId());
		}
	}
	
	@Test
	public void testAddProduct() {
		Product added = newProduct(1);
		verify(mockedShoppingService, never()).addProduct(added);

		shoppingController.addProduct(added);
		verify(mockedShoppingService, times(1)).addProduct(added);
	}
	
	@Test
	public void testEditProduct() {
		Product modified = newProduct(1);
		verify(mockedShoppingService, never()).updateProduct(1, modified);
		
		shoppingController.editProduct(1, modified);
		verify(mockedShoppingService, times(1)).updateProduct(1, modified);
	}
	
	@Test
	public void testDeleteProduct() {
		verify(mockedShoppingService, never()).deleteProduct(1);
		
		shoppingController.deleteProduct(1);
		verify(mockedShoppingService, times(1)).deleteProduct(1);
	}
	
	static Product newProduct(long id) {
		Product mocked = mock(Product.class);
		when(mocked.getId()).thenReturn(id);
		return mocked;
	}

	@Test
	public void testListCategories() {
		{
			doReturn(new ArrayList<Category>()).when(mockedShoppingService).listCategories();

			List<Category> first = shoppingController.listCategories();
			assertTrue(first.isEmpty());
		}
		{
			doReturn(ImmutableList.of(newCategory(1), newCategory(2))).when(mockedShoppingService).listCategories();

			List<Category> second = shoppingController.listCategories();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());

			assertEquals(1, (long) second.get(0).getId());
			assertEquals(2, (long) second.get(1).getId());
		}
	}

	@Test
	public void testAddCategory() {
		Category added = newCategory(1);
		verify(mockedShoppingService, never()).addCategory(added);

		shoppingController.addCategory(added);
		verify(mockedShoppingService, times(1)).addCategory(added);
	}

	@Test
	public void testEditCategory() {
		Category modified = newCategory(1);
		verify(mockedShoppingService, never()).updateCategory(1, modified);

		shoppingController.editCategory(1, modified);
		verify(mockedShoppingService, times(1)).updateCategory(1, modified);
	}

	@Test
	public void testDeleteCategory() {
		verify(mockedShoppingService, never()).deleteCategory(1);

		shoppingController.deleteCategory(1);
		verify(mockedShoppingService, times(1)).deleteCategory(1);
	}

	static Category newCategory(long id) {
		Category mocked = mock(Category.class);
		when(mocked.getId()).thenReturn(id);
		return mocked;
	}
}
