package controller;

import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.channel;
import model.rss;


public class connector {
	
    public List<channel> getObjectFromUrl(URL url) throws JAXBException {
    	
    	System.out.println(url.toString());
        rss rs = new rss();
        JAXBContext context = JAXBContext.newInstance(rss.class);
        Unmarshaller um = context.createUnmarshaller();
        rs = (rss) um.unmarshal(url);
        return rs.getLit();
    }

}
