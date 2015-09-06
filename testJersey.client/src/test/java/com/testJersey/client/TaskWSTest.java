package com.testJersey.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.junit.Test;













import com.google.gson.Gson;
import com.testJersey.client.entities.Task;

import junit.framework.TestCase;

public class TaskWSTest extends TestCase{

	
	private Gson gson = new Gson();	
	
	
	TaskWSConsummer taskWSConsummer;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		taskWSConsummer = new TaskWSConsummer();
		
		List<Task> tasks = taskWSConsummer.getAll();
		for (Task task : tasks){
			taskWSConsummer.delete(task.getUuid());
		}
		
		if (taskWSConsummer.getAll().size() != 0){
			fail();
		}	
	}
	
	
	@Test
	public void testDeleteTask(){
		try{
			Task task = new Task("", "title", "description", false);
			task = taskWSConsummer.post(task);

			Task task2 = new Task("", "title2", "description2", false);
			task2 = taskWSConsummer.post(task2);

			List<Task> tasks = taskWSConsummer.getAll();
			assertEquals(2, tasks.size());
			
			taskWSConsummer.delete(task.getUuid());
			
			tasks = taskWSConsummer.getAll();
			assertEquals(1, tasks.size());
			assertEquals(task2.getUuid(), tasks.get(0).getUuid());
			assertEquals("title2", tasks.get(0).getTitle());

		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testGetAllTasks(){
		try{
			Task task = new Task("", "title", "description", false);
			task = taskWSConsummer.post(task);

			Task task2 = new Task("", "title2", "description2", false);
			task2 = taskWSConsummer.post(task2);
			
			Task task3 = new Task("", "title3", "description3", false);
			task3 = taskWSConsummer.post(task3);

			List<Task> tasks = taskWSConsummer.getAll();
			assertEquals(3, tasks.size());
			
			Set<String> set = new HashSet<String>();
			set.add("title");
			set.add("title2");
			set.add("title3");
			
			for (Task returnedTask : tasks){
				set.remove(returnedTask.getTitle());		
			}
			
			assertEquals(0, set.size());
			
		} catch(Exception e) {
			fail();
		}
	}
	
	
	@Test
	public void testPostTask(){
		try{
			Task task = new Task("", "title", "description", false);
			task = taskWSConsummer.post(task);
			
			Task task2 = new Task("", "title2", "description2", false);
			task = taskWSConsummer.post(task2);
			
			List<Task> tasks = taskWSConsummer.getAll();
			
			assertEquals(2, tasks.size());
			assertFalse(tasks.get(0).getTitle().equals(tasks.get(1).getTitle()));
			
			
		} catch(Exception e) {
			fail();
		}
	}
	
	
	@Test
	public void testPutTask(){
		try{
			Task task = new Task("", "title", "description", false);
			task = taskWSConsummer.post(task);
				
			Task returnedTask1 = taskWSConsummer.get(task.getUuid());
			assertEquals("description", returnedTask1.getDescription());
			
			task.setDescription("Updated description");

			taskWSConsummer.put(task);
			Task returnedTask = taskWSConsummer.get(task.getUuid());
			
			assertEquals(task.getUuid(), returnedTask.getUuid());
			assertEquals("Updated description", returnedTask.getDescription());
			assertEquals(task.getDescription(), returnedTask.getDescription());
		
			List<Task> tasks = taskWSConsummer.getAll();
			assertEquals(1, tasks.size());
			
		} catch(Exception e) {
			fail();
		}
	}

	
	@Test
	public void testPutTaskException(){
		try{
			TaskWSConsummer taskWSConsummer = new TaskWSConsummer();

			Task task = new Task("1", "title", "description", false);
			task = taskWSConsummer.put(task);
			
			fail();
		
		} catch(Exception e) {
			assertEquals(e.getMessage(), "404");
		}
	}
	
}
