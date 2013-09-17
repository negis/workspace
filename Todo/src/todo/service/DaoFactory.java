package todo.service;

import java.util.Calendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import todo.entity.Todo;

/**
 * Factory for DAO objects.
 * This encapsulates creation of objects needed
 * for a DAO, such as EntityManagerFactory
 * and EntityManager.
 * 
 * @author James Brucker
 *
 */
public class DaoFactory {
	// use JPA Dao to save Todo or use memory-only Dao?
	private static final boolean USE_JPA = false;
	/** name of persistence unit must match the name in persistence.xml. This is not the database name. */
	private static final String PERSISTENCE_UNIT = "todo";
	// singleton instance of this factory
	private static DaoFactory factory;
	/* package scope */
	private EntityManagerFactory emf = null;
	private TodoDao dao;

	/**
	 * Initialize a new DaoFactory.
	 * We naively create the singleton TodoDao here, 
	 * using either a memory-only implementation (TodoDaoMem)
	 * or JPA implementation (TodoDaoJpa).
	 */
	private DaoFactory() {
		if (USE_JPA) {
			// Use this code for JPA
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			dao = new TodoDaoJpa(emf);
		}
		else {
			// Dao that "saves" Todo in memory only
			dao = new TodoDaoMem();
		}
		init(dao);
	}
	
	public static DaoFactory getInstance() {
		if (factory == null) factory = new DaoFactory();
		return factory;
	}
	
	
	/** Add some Todo if nothing has been saved yet. */
	public void init(TodoDao dao) {
		if (dao.size() <= 0) {
			dao.save( new Todo("Write a RESTful Todo Resource", new java.util.Date(), 1) );
			Calendar nextweek = Calendar.getInstance();
			nextweek.add(Calendar.DATE, 7);
			dao.save( new Todo("Study REST service design", nextweek.getTime(), 2) );
		}
	}
	
	/**
	 * Return an instance of a Todo DAO.
	 * @return a TodoDAO
	 */
	public TodoDao getTodoDao() {
		return dao;
	}
	
	public void shutdown() {
		dao.close();
	}
}
