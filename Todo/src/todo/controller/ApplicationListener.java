package todo.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import todo.service.DaoFactory;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
public class ApplicationListener implements ServletContextListener {
    /**
     * Default constructor. 
     */
    public ApplicationListener() {
        // nothing to initialize here
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
    	
// EntityManager and factory are created in TodoDaoFactory if JPA is selected.
    	
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo");
//        EntityManager em = emf.createEntityManager();
//        ServletContext context = sce.getServletContext();
//        context.setAttribute("EntityManagerFactory", emf);
//        context.setAttribute("EntityManager", em);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
    	// for JPA, close the database connection 
    	// if we had created the EMF in contextInitialized() we could close it here.
        DaoFactory.getInstance().getTodoDao().close();
    }
}
