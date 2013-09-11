package model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="student")
@XmlAccessorType(XmlAccessType.FIELD)

public class Student {
	@XmlAttribute
	//@XmlElement(name="id")
	private String id;
	@XmlElement(name="lastname")
	private String lastname;
	@XmlElement(name="firstname")
	private String firstname;
	@XmlElement(name="email")
	private String email;
	
	public Student(){
		super();
	}
	
	public Student(String firstname, String lastname ,String hometown) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = hometown;
	}
	
	public String getName() {
		return firstname;
	}
	public void setName(String name) {
		this.firstname = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHometown() {
		return email;
	}
	public void setHometown(String hometown) {
		this.email = hometown;
	}
	
	

}
