package test;

import java.util.List;
import todo.entity.Todo;
import todo.service.DaoFactory;
import todo.service.TodoDao;
/**
 * Simple test to verify DAO and JPA are working.
 * @author James Brucker
 */
public class TodoDaoTest {

	public static void main(String[] args) {
		TodoDao dao = DaoFactory.getInstance().getTodoDao();
		
		int size = dao.size();
		
		System.out.printf("Dao contains %d items\n",  size);
		
		List<Todo> todos =  dao.findAll();
		for(Todo todo: todos) System.out.println( todo );
	}
}
