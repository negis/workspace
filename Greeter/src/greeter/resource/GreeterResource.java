package greeter.resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;

import model.Student;

/**
 * Greet a user
 */
@Path("/")
public class GreeterResource {
	@Context
	UriInfo uriInfo;  // info about our URI
	// map of names to Greetings
	Map<String,Greeting> map = new HashMap<String, Greeting>();
	
	ArrayList<Student> studentList = new ArrayList();
	
	
    @GET
    @Produces("text/plain")
    public String getGreeting() {
        return "Hello, web server";
    }
    
    @GET
    @Path("/greeting/{name}")
    @Produces(MediaType.TEXT_PLAIN) // "text/plain"
    public String getGreeting( @PathParam("name") String name) {
    	return "Hello, "+name;
    }
    
    @GET
    @Path("/addStudent/{inpString}")
    public boolean createStudent(@PathParam("inpString") String inpString){
    	int tempId = Integer.parseInt (inpString.split(" ")[0]);
    	String tempStudentName = inpString.split(" ")[1];
    	Student tempStudent  = new Student(tempId,tempStudentName);
    	
    	if (studentList.contains(tempStudent)){
    		System.out.println("Already Contain Student");
    		return false;
    	}
    	studentList.add(tempStudent);
    	return true;
    	
    	
    }
    
    
    
    @PUT
    @Path("{name}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putGreeting(@PathParam("name") String name, JAXBElement<Greeting> jax)
    {
    	Greeting greeting = (Greeting)jax.getValue();
    	// save or update Greeting
    	map.put(name, greeting);
    	// what is the URI of this greeting?
    	URI location = uriInfo.getAbsolutePath();
    	return Response.created(location).build();
    }
    
    

}
