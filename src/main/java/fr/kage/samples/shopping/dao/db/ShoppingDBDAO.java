package fr.kage.samples.shopping.dao.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.annotations.VisibleForTesting;

import fr.kage.samples.shopping.dao.ShoppingDAO;
import fr.kage.samples.shopping.model.Category;
import fr.kage.samples.shopping.model.Element;

@Repository("shoppingDAO")
@Transactional
public class ShoppingDBDAO implements ShoppingDAO {
	
	private Session session;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		session = sessionFactory.openSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Element> listElements() {
		return session.createCriteria(Element.class).list();
	}

	@Override
	@Transactional(readOnly=false)
	public void addElement(Element element) {
		session.save(element);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateElement(long id, Element element) {
		Element toUpdate = getElementById(id);
		toUpdate.updateFrom(element);
		session.update(toUpdate);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteElement(long id) {
		Transaction tx = session.beginTransaction();
		session.delete(getElementById(id));
		tx.commit();
	}

	private Element getElementById(long id) {
		return (Element) session.load(Element.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> listCategories() {
		return session.createCriteria(Category.class).list();
	}

	@Override
	public void addCategory(Category category) {
		session.save(category);
	}

	@Override
	public void updateCategory(long id, Category category) {
		Category toUpdate = getCategoryById(id);
		toUpdate.updateFrom(category);
		session.update(toUpdate);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteCategory(long id) {
		Transaction tx = session.beginTransaction();
		session.delete(getCategoryById(id));
		tx.commit();
	}

	private Category getCategoryById(long id) {
		return (Category) session.load(Category.class, id);
	}
	
	@Transactional(readOnly=false)
	@VisibleForTesting
	public void cleanAllEntities() {
		Transaction tx = session.beginTransaction();
		session.createQuery("DELETE FROM Element").executeUpdate();
		session.createQuery("DELETE FROM Category").executeUpdate();
		tx.commit();
	}

}
