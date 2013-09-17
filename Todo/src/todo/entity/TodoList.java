package todo.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;
/**
 * A wrapper for serializing a list.
 * the desired output is:
 * <todolist>
 *   <todo id="1">
 *       <description>...</description>
 *       ...
 *   </todo>
 *   <todo id="2">
 *       <description>...</description>
 *   </todo>
 * </todolist>
 * 
 * @author James Brucker
 */
@XmlRootElement(name="todolist")
@XmlAccessorType(XmlAccessType.FIELD)
public class TodoList implements Serializable {
	@XmlElement(name="todo")
	List<Todo> todos;

	public TodoList() {
		todos = new java.util.ArrayList<Todo>();
	}
	
	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	/**
	 * Add a todo to the list, if it isn't in list yet.
	 * @param atodo the Todo to add
	 * @return true if added to list, false otherwise
	 */
	public boolean add(Todo atodo) {
		if (todos.contains(atodo)) return false;
		return this.todos.add(atodo);
	}
}
