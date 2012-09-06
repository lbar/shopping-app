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
import fr.kage.samples.shopping.model.Product;

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
	public void testListProducts() {
		{
			List<Product> first = shoppingDBDAO.listProducts();
			assertTrue(first.isEmpty());
		}
		{
			shoppingDBDAO.addProduct(newProduct("product1", 2));
			shoppingDBDAO.addProduct(newProduct("product2", 5));
			
			List<Product> second = shoppingDBDAO.listProducts();
			assertFalse(second.isEmpty());
			assertEquals(2, second.size());
		}
	}
	
	@Test
	public void testAddProduct() {
		assertTrue(shoppingDBDAO.listProducts().isEmpty());
		{
			Product added = newProduct("product", 3);
			shoppingDBDAO.addProduct(added);
			assertEquals(added, shoppingDBDAO.listProducts().get(0));
		}
	}
	
	@Test
	public void testEditProduct() {
		Product added = newProduct("product1", 4);
		shoppingDBDAO.addProduct(added);
		assertEquals(added, shoppingDBDAO.listProducts().get(0));
		
		Product modified = newProduct("product2", 2);
		modified.setId(added.getId());
		assertNotSame(modified, added);
		shoppingDBDAO.updateProduct(added.getId(), modified);
		assertEquals(modified, shoppingDBDAO.listProducts().get(0));
	}
	
	@Test
	public void testDeleteProduct() {
		Product added = newProduct("product1", 4);
		shoppingDBDAO.addProduct(added);
		assertEquals(added, shoppingDBDAO.listProducts().get(0));
		
		shoppingDBDAO.deleteProduct(added.getId());
		{
			List<Product> allProducts = shoppingDBDAO.listProducts();
			assertTrue(allProducts.isEmpty());
		}
	}
	
	static Product newProduct(String name, int amount) {
		final Product mocked = new Product();
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
