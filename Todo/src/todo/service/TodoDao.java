package todo.service;

import java.util.List;
import todo.entity.Todo;
/**
 * Data Access Object interface for persisting Todo objects.
 * @author James Brucker
 */
public interface TodoDao {

	/**
	 * Find a todo  an id.
	 * @param id is the ID of todo to return.
	 * @return matching todo or null if no match for id.
	 */
	public Todo find(int id);

	/**
	 * Save or update a Todo.
	 * If the todo.id is 0 it is considered a new Todo
	 * and saved.
	 * If id > 0, an existing todo is searched for
	 * and updated if found.  If id > 0 but no existing
	 * Todo, what happens is implementation dependent.
	 * @param todo is the Todo to save or update.
	 */
	public void save(Todo todo);

	/**
	 * Return all todo objects.
	 * @return List of todo objects
	 */
	public List<Todo> findAll();

	/**
	 * Remove a Todo from persistent storage.
	 * No error returned if the Todo is not found 
	 * in persistent storage.
	 * @param todo a todo to remove.
	 */
	public void delete(Todo todo);

	/**
	 * Delete a persisted Todo having given id.
	 * Only the first match is deleted, but there should
	 * be only one todo with given id.
	 * @param id is the ID number of todo to delete.
	 */
	public void delete(int id);
	
	/**
	 * Get number of Todo in persistent storage.
	 * @return number of Todo that are saved.
	 */
	public int size();
	
	/**
	 * Shutdown operation of DAO.
	 * For persistence, use to close database connection.
	 */
	public void close();

}