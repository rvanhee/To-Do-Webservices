package com.testJersey.client;

import java.util.List;

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
			
			
			task.setDescription("Updated description");

			Task returnedTask = taskWSConsummer.put(task);
			
			assertEquals(task.getUuid(), returnedTask.getUuid());
			assertEquals("Updated description", returnedTask.getDescription());
			assertEquals(task.getDescription(), returnedTask.getDescription());
		
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
