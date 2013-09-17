package todo.resource;
import java.net.URI;
import java.security.Principal;
import java.util.List;
//import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import todo.entity.Todo;
import todo.service.DaoFactory;
import todo.service.TodoDao;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAttribute;
  
@Path("/todo")
public class TodoResourceStart {
	private TodoDao dao;
	@Context
	UriInfo uriInfo;  // info about our URI
	
	public TodoResourceStart() {
		dao = DaoFactory.getInstance().getTodoDao();
	}
	
	/**
	 * Get Todos, with optional parameter to limit number returned.
	 * 
	 */
	@GET
	public List<Todo> getTodos( ) {
		//TODO
		return dao.findAll();
	}
	
	/**
	 * Save a new "todo" submitted by form.
	 * @param description a description of this todo
	 * @param date the date due
	 * @param priority is the priority (1=highest) of this todo
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response post(
			@FormParam("description") String description,
			@FormParam("duedate") String date,
			@FormParam("priority") int priority,
			@Context HttpServletRequest request)
			{
		
	
		Todo todo = new Todo(description, date, priority);
		dao.save(todo);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		URI uri = builder.path( ""+todo.getId() ).build();

		System.out.println("POST: uri = " + uri.toString() );
		Response response = Response.created(uri).build();
		System.out.println("Response = " + response );
		
		return response; 
	}

	
	/**
	 * Edit a new "todo" submitted by form.
	 * ****note that it will update old todo if new todo have same id.
	 * @param description a description of this todo.
	 * @param date the date due.
	 * @param priority is the priority (1=highest) of this todo.
	 * @param id is the id to reference todo in database.
	 */
	
	@PUT
	@Path("/{id}")
	@Consumes("application/x-www-form-urlencoded")
	public void put(
			@FormParam("description") String description,
			@FormParam("duedate") String date,
			@FormParam("priority") int priority,
			@Context HttpServletRequest request ,
			@PathParam("id") int id ) {
		Todo todo = new Todo(description, date, priority);
		todo.setId(id);		
		dao.save(todo);
			
			
	}
	
	/**
	 * delete a new "todo" referred to id in URL.
	 * @param id is the id that refer to todo in database
	 */
	
	@DELETE
	@Path("/{id}")
	public void delete(
			@PathParam("id") int id
			) {
		dao.delete(id);
	}
	
	/**
	 * Save a new "todo" submitted by XML.
	 * @param todo object which automatically mapped from framework.
	 */
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void postXml(Todo todo){
		dao.save(todo);
		
	}
	
	
}
