package todo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import todo.entity.Todo;

/**
 * Data access object using a List to save Todo objects.
 * @author James Brucker
 *
 */
public class TodoDaoMem implements TodoDao {
	private List<Todo> todos;
	// print log messages?
	private static final boolean LOGGING = true;
	// next ID to assign to a Todo added to list.
	private int nextId;
	
	public TodoDaoMem() {
		todos = new ArrayList<Todo>();
		nextId = 1;
	}

	/**
	 * @see todo.service.TodoDao#find(int)
	 */
	public Todo find(int id) {
		for(Todo todo: todos) {
			if (todo.getId() == id) return todo;
		}
		return null;
	}
	
	/**
	 * @see todo.service.TodoDao#save(todo.entity.Todo)
	 */
	public void save(Todo todo) {
		if (todo == null) return;
		if (todo.getId() == 0) {
			log("adding new todo with id "+nextId);
			todo.setId(nextId);
			nextId++;
			todos.add(todo);
		}
		else {
			Todo t = find(todo.getId());
			if (t != null) {
				log("update existing todo with id "+t.getId());
				if (t == todo) return;
				//replace old todo with new instance
				todos.remove(t);
				todos.add(todo);
			}
			else log("update todo failed. Unknown id "+todo.getId());
			// user assigned id
			nextId = Math.max(nextId, todo.getId()+1);
		}
	}
	
	/**
	 * @see todo.service.TodoDao#findAll()
	 * Caller should not modify the list, since it may
	 * corrupt the "persisted" list of Todo.
	 */
	public List<Todo> findAll() {
		return todos;
	}
	
	/**
	 * @see todo.service.TodoDao#delete(todo.entity.Todo)
	 */
	public void delete(Todo todo) {
		if ( todos.remove(todo) ) {
			log("Deleted todo "+todo.getDescription());
			// reset id to zero to indicate not persisted
			todo.setId(0);
		}
		else
			log("Remove todo failed for todo "+todo.getId());

	}
	
	/**
	 * @see todo.service.TodoDao#delete(int)
	 */
	public void delete(int id) {
		for(int k=0; k<todos.size(); k++) {
			Todo todo = todos.get(k);
			if (todo.getId() == id) {
				log("removing todo id="+id);
				todos.remove(k);
				// reset id to zero to indicate not persisted
				todo.setId(0);
				return;
			}
		}
		log("delete failed. No todo with id="+id);
	}
	
	private void log(String msg) {
		if (LOGGING) System.out.println("TodoDao: "+msg);
	}

	/**
	 * @see todo.service.TodoDao#size()
	 */
	public int size() {
		return todos.size();
	}
	
	public void close() {
		// nothing to do.
	}
}
