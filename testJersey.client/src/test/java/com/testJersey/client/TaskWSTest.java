package com.testJersey.client;

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
		
		//task
		
		
	}
	
	
	@Test
	public void testPutTask(){
		try{
			Task task = new Task("uuid1", "title", "description", false);
			
			task = taskWSConsummer.put(task);
			assertEquals(task.getUuid(), 1);
		
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
