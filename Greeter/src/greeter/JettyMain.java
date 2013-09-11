package greeter;

import java.net.BindException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * Deploy a RESTful server using Jersey in an embedded Jetty server.
 * 
 * Requires: Jetty 8 JARs.
 * 
 * @author James Brucker
 *
 */
public class JettyMain {
	private static Server server;
	
	
	private static void startServer( int port ) throws BindException, Exception
	{		
		//Class clazz =  ServletContainer.class; // uncomment to help Eclipse locate import
		server = new Server(port);
		ServletHolder sh = new ServletHolder(ServletContainer.class);
		
		// set this parameter to a colon-delimited list of package names
		// the packages are where your REST resources are
		final String RESOURCES = "greeter.resource";
		
// Jersey 1.x:
		sh.setInitParameter( 
				"com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig");
		sh.setInitParameter(
				"com.sun.jersey.config.property.packages", RESOURCES );
// Jersey 2.x:
		sh.setInitParameter(
				"org.glassfish.jersey.config.property.resourceConfigClass",
				"org.glassfish.jersey.api.core.PackagesResourceConfig");
		sh.setInitParameter(
				"org.glassfish.jersey.config.property.packages", RESOURCES );
 
// uncomment these to enable tracing of requests and responses
		
//		sh.setInitParameter("com.sun.jersey.config.feature.Debug", "true");
//		sh.setInitParameter("com.sun.jersey.config.feature.Trace", "true");
//		
//		sh.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", 
//				"com.sun.jersey.api.container.filter.LoggingFilter");
//		sh.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", 
//				"com.sun.jersey.api.container.filter.LoggingFilter");
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

// Map the context path to servlet
		
		context.setContextPath("/");
		context.addServlet(sh, "/*");
		
		server.setHandler(context);
 
		QueuedThreadPool qtp = new QueuedThreadPool(10);
		qtp.setName("ApiServe");
		server.setThreadPool(qtp);
        
		server.start();
		
	}
	
	public static void stopServer( ) throws Exception {
		if (server != null) server.stop();
	}
	
	public static void main(String[] args) throws BindException, Exception {
		int port = 1025;
		System.out.println("Starting server...");
		startServer( port );
		System.out.printf("Server started on port %d\n",  port);
		
		//TODO add your own code to stop the server
	}
}
