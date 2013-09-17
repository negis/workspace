package todo.service;

import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;
import todo.entity.*;

/**
 * Data access object using JPA.
 * @author James Brucker
 *
 */
public class TodoDaoJpa implements TodoDao {
	// print log messages?
	private static final boolean LOGGING = true;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TodoDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
		this.em = emf.createEntityManager();
	}
	
	/**
	 * @see todo.service.TodoDao#find(int)
	 */
	public Todo find(int id) {
		return em.find(Todo.class, id);
	}
	
	/**
	 * @see todo.service.TodoDao#save(todo.entity.Todo)
	 */
	public void save(Todo todo) {
		EntityTransaction tx = em.getTransaction();
		// synchronize transactions
		synchronized(tx) {
			try {
				tx.begin();
				if (todo.getId()>0) em.merge(todo);
				else em.persist(todo);
				tx.commit();
			} catch(EntityExistsException ex) {
				log("save failed: "+ex.getMessage());
				if (tx.isActive()) tx.rollback();
			}
		}
	}
	
	/**
	 * @see todo.service.TodoDao#findAll()
	 */
	public List<Todo> findAll() {
		// This is JPA Query Language, not SQL
		Query query = em.createQuery("SELECT t FROM Todo t");
		return query.getResultList();
	}
	
	/**
	 * @see todo.service.TodoDao#delete(todo.entity.Todo)
	 */
	public void delete(Todo todo) {
		EntityTransaction tx = em.getTransaction();
		// synchronize transactions
		synchronized(tx) {
			tx.begin();
			em.remove(todo);
			tx.commit();
		}
		// do we need to set ID to 0? JPA should do it.
		todo.setId(0);
	}
	
	/**
	 * @see todo.service.TodoDao#delete(int)
	 */
	public void delete(int id) {
		Todo todo = this.find(id);
		if (todo != null) this.delete(todo);
	}
	
	private void log(String msg) {
		if (!LOGGING) return;
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.warning(msg);
	}
	
	/**
	 * @see todo.service.TodoDao#size()
	 */
	public int size() {
		Query query = em.createQuery("SELECT count(t) FROM Todo t");
		Object obj = query.getSingleResult(); // should be a Long
		if (obj instanceof Number) return ((Number)obj).intValue();
		
		log("TodoDao.size() returned a "+obj.getClass().getName()+" with value "+obj.toString());
		return 0;
	}
	
	public void close() {
		try {
			em.close();
			emf.close();
		} catch (Exception ex) {
			// forget it
		}
	}
}
