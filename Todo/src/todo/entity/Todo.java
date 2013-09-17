package todo.entity;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * The persistent class for the todo database table.
 * 
 */
@Entity
@Table(name="todo")
@XmlRootElement
public class Todo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	/** description of this todo */
	private String description;
	/** owner of the todo */
	private String username;

	//@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private String dueDate;
    private int priority;
    private boolean done;

	public Todo() {
		this("","",5);
    }
  
	/**
	 * @param description
	 * @param due due date as String
	 * @param priority
	 */
	public Todo(String description, String due, int priority) {
		this.description = description;
		this.dueDate = due;
		this.priority = priority;
		this.done = false;
	}

	public Todo(String description, Date dueDate, int priority) {
		this(description, String.format("%tF", dueDate), priority);
	}
	
	@XmlAttribute
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = String.format("%tF", dueDate);
	}

	public void setDueDate(String due) {
		this.dueDate = due;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s. Due %s priority %d",
				id, description, dueDate, priority);
	}
}