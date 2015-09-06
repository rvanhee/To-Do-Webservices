package com.testJersey.interfaces;

import java.util.List;

import com.testJersey.entities.Task;

public interface TaskServices {

	
	public Task save(Task task); 
	public List<Task> getAll();
	public Task get(String uuid);
	public Task update(Task task); 
	public boolean delete(String uuid); 
	
}
