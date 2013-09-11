package model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBTest {
	public static void main(String[]arg){
		
		try {
			JAXBContext context = JAXBContext.newInstance(Student.class);
			Student chuphan = new Student("Chuphan","Tharacheewin", "mail@mail.com");
			chuphan.setId("5410547580000000");
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  true);
			m.marshal(chuphan, System.out);

			// create connection
			
			URL url = new URL("http://158.108.138.246:2000/students");
			URLConnection con = url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout( 20000 );
			con.setReadTimeout( 20000 );
			con.setUseCaches (false);
			con.setDefaultUseCaches (false);
			con.setRequestProperty ( "Content-Type", "text/xml" );
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			m.marshal(chuphan, wr);
			wr.write("\n");
	        wr.flush(); 
	        
	        // response from server
	        
	        InputStreamReader reader = new InputStreamReader( con.getInputStream());
	        StringBuilder buf = new StringBuilder();
	        char[] cbuf = new char[ 2048 ];
	        int num;
	        while ( -1 != (num=reader.read( cbuf )))
	        	{
	        	buf.append( cbuf, 0, num );
	        	}
	        
	        System.out.print(buf.toString());
	        
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
		
	}

}
