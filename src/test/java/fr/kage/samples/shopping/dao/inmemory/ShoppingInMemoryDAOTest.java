package fr.kage.samples.shopping.dao.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Product;

public class ShoppingInMemoryDAOTest {
	
	private ShoppingInMemoryDAO shoppingInMemoryDAO;
	
	@Before
	public void setUp() {
		shoppingInMemoryDAO = new ShoppingInMemoryDAO();
	}

	@Test
	public void testListProducts() {
		{
			List<Product> first = shoppingInMemoryDAO.listProducts();
			assertTrue(first.isEmpty());
		}
		{
			shoppingInMemoryDAO.addProduct(newProduct());
			shoppingInMemoryDAO.addProduct(newProduct());
			
			List<Product> second = shoppingInMemoryDAO.listProducts();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
		}
	}
	
	@Test
	public void testAddProduct() {
		assertTrue(shoppingInMemoryDAO.listProducts().isEmpty());
		{
			Product added = newProduct();
			shoppingInMemoryDAO.addProduct(added);
			assertEquals(added, shoppingInMemoryDAO.listProducts().get(0));
		}
	}
	
	@Test
	public void testEditProduct() {
		Product added = newProduct();
		shoppingInMemoryDAO.addProduct(added);
		assertEquals(added, shoppingInMemoryDAO.listProducts().get(0));
		
		Product modified = newProduct();
		assertNotSame(modified, added);
		shoppingInMemoryDAO.updateProduct(1, modified);
		assertEquals(modified, shoppingInMemoryDAO.listProducts().get(0));
	}
	
	@Test
	public void testDeleteProduct() {
		Product added = newProduct();
		shoppingInMemoryDAO.addProduct(added);
		assertEquals(added, shoppingInMemoryDAO.listProducts().get(0));
		
		shoppingInMemoryDAO.deleteProduct(1);
		assertTrue(shoppingInMemoryDAO.listProducts().isEmpty());
	}
	
	static Product newProduct() {
		final Product mocked = mock(Product.class);
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				when(mocked.getId()).thenReturn((Long) invocation.getArguments()[0]);
				return null;
			}
		}).when(mocked).setId(anyLong());
		when(mocked.getId()).thenReturn(null); // by default
		return mocked;
	}
	
	@Test
	public void testListCategories() {
		{
			List<Category> first = shoppingInMemoryDAO.listCategories();
			assertTrue(first.isEmpty());
		}
		{
			shoppingInMemoryDAO.addCategory(newCategory());
			shoppingInMemoryDAO.addCategory(newCategory());
			
			List<Category> second = shoppingInMemoryDAO.listCategories();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
		}
	}
	
	@Test
	public void testAddCategory() {
		assertTrue(shoppingInMemoryDAO.listCategories().isEmpty());
		{
			Category added = newCategory();
			shoppingInMemoryDAO.addCategory(added);
			assertEquals(added, shoppingInMemoryDAO.listCategories().get(0));
		}
	}
	
	@Test
	public void testEditCategory() {
		Category added = newCategory();
		shoppingInMemoryDAO.addCategory(added);
		assertEquals(added, shoppingInMemoryDAO.listCategories().get(0));
		
		Category modified = newCategory();
		assertNotSame(modified, added);
		shoppingInMemoryDAO.updateCategory(added.getId(), modified);
		assertEquals(modified, shoppingInMemoryDAO.listCategories().get(0));
	}
	
	@Test
	public void testDeleteCategory() {
		Category added = newCategory();
		shoppingInMemoryDAO.addCategory(added);
		assertEquals(added, shoppingInMemoryDAO.listCategories().get(0));
		
		shoppingInMemoryDAO.deleteCategory(added.getId());
		assertTrue(shoppingInMemoryDAO.listCategories().isEmpty());
	}
	
	static Category newCategory() {
		final Category mocked = mock(Category.class);
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				when(mocked.getId()).thenReturn((Long) invocation.getArguments()[0]);
				return null;
			}
		}).when(mocked).setId(anyLong());
		when(mocked.getId()).thenReturn(null); // by default
		return mocked;
	}
}
