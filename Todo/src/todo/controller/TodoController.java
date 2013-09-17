package todo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import todo.entity.Todo;
import todo.service.DaoFactory;
import todo.service.TodoDao;

/**
 * Servlet implementation of Controller for Todo app.
 * This class handles ordinary web requests submitted by web browser.
 */
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String VIEW_DIR = "";
	private TodoDao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoController() {
        super();
    }
    
	@Override
	public void init( ) throws ServletException {
		ServletConfig config = getServletConfig();
		VIEW_DIR = config.getInitParameter("viewDirectory");
		dao = DaoFactory.getInstance().getTodoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestPath = request.getServletPath();
		String view = "/index.jsp";
		// Java Security (JAAS) 
		Principal prince = request.getUserPrincipal( );
		String username =  (prince!=null)?prince.getName():"Web Surfer";
		request.setAttribute("user", username);
		
		//System.out.println("Request from "+prince);
		
		if (requestPath.equals("/view")) {
			request.setAttribute("todolist", dao.findAll() );
			view = VIEW_DIR+"/todolist.jsp";
		}
		else if (requestPath.equals("/add")) {
			// display add form
			view = VIEW_DIR+"/edittodo.jsp";  // make it match JSP name
		}
		else if (requestPath.equals("/edit")) {
			// display edit form with existing todo (if any)
			String ids = request.getParameter("id");
			if (ids != null && ! ids.isEmpty() ) {
				int id = Integer.parseInt(ids);
				Todo todo = dao.find(id);
				if (todo != null) request.setAttribute("todo",todo);
			}
			view = VIEW_DIR+"/edittodo.jsp";
		}
		
		else {
			//TODO unknown request -- log it
			response.sendError(response.SC_BAD_REQUEST);
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * A POST request is received when user wants to add a new Todo or update existing Todo.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// handle posting of todo
		String requestPath = request.getServletPath();
		// Java Security (JAAS) 
		Principal prince = request.getUserPrincipal( );
		String username =  (prince!=null)?prince.getName():"Web Surfer";
		request.setAttribute("user", username);
				
		if ( requestPath.equals("/edit") || requestPath.equals("/add") ) {
			// check for edit an existing todo
			String ids = request.getParameter("id");
			int id = 0; // a new todo
			if (ids != null && ! ids.isEmpty()) {
				try {
					id = Integer.parseInt(ids);
				} catch(NumberFormatException pe) { /* handle it */ }
			}
			String description = request.getParameter("description");
			// date is not verified. We just store as string.
			String datestring = request.getParameter("duedate");
			if (datestring == null || datestring.isEmpty())	
				datestring = String.format("%tF", tomorrow());
			String spriority = request.getParameter("priority");
			int priority = 0;
			if (spriority != null && spriority.matches("\\d+") )
				priority = Integer.parseInt(spriority);
			String sdone = request.getParameter("done");
			System.out.println("done = "+sdone);
			boolean done  = sdone != null && ! sdone.isEmpty(); // don't rely on string value of checkbox

			Todo todo;
			if (id>0) {
				System.out.println("Updating todo with id "+id+" done="+done);
				todo = dao.find(id);
				if (todo == null) { response.sendError(response.SC_NOT_FOUND); return; }
				todo.setDescription(description);
				todo.setDueDate(datestring);
				todo.setPriority(priority);
				todo.setDone(done);
			}
			else {
				log("Creating new todo");
				todo = new Todo(description, datestring, priority);
			}
			dao.save(todo);
		}
		else {
			//TODO unknown request -- log it
			response.sendError(response.SC_BAD_REQUEST);
		}
		// now show list of all todo
		request.setAttribute("todolist", dao.findAll() );
		String view = VIEW_DIR+"/todolist.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private Date tomorrow() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return c.getTime(); //  getTime() creates Date from Calendar
	}
}
