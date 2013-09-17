package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/* 
 * Channel Class that contain items
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "channel")
public class channel {
	@XmlElement (name = "item", type = item.class)
	private List<item> lit = new ArrayList<item>();
	
	public channel(){}

	public channel(List<item> lit) {
		super();
		this.lit = lit;
	}

	public List<item> getLit() {
		return lit;
	}

	public void setLit(List<item> lit) {
		this.lit = lit;
	}

}
