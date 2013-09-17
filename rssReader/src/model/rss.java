package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * rss wrapped object which wrapped channel.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rss")
public class rss {
	@XmlElement (name = "channel" , type = channel.class)
	private List<channel> lit = new ArrayList<channel>();
	
	public rss(){}

	public rss(List<channel> lit) {
		super();
		this.lit = lit;
	}

	public void setLit(List<channel> lit) {
		this.lit = lit;
	}

	public List<channel> getLit() {
		return lit;
	}

}
