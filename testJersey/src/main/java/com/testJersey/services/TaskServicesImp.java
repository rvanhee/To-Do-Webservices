package com.testJersey.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Component;

import com.testJersey.entities.Task;
import com.testJersey.interfaces.TaskServices;

@Component
public class TaskServicesImp implements TaskServices{

	private static Map<String, Task> map = new HashMap<String, Task>();
	
	
	@Override
	public synchronized Task save(Task task) {
		task.setUuid(UUID.randomUUID().toString());
		map.put(task.getUuid(), task);
		return task;
	}

	
	@Override
	public List<Task> getAll() {
		return new ArrayList<Task>(map.values());
	}

	
	@Override
	public Task get(String uuid) {
		
		Task task = map.get(uuid);		
		if (task == null){
			throw new NotFoundException();
		}
		return task;
	}

	
	@Override
	public synchronized Task update(Task task){
		if (!map.containsKey(task.getUuid())){
			throw new NotFoundException();
		}
		
		map.put(task.getUuid(), task);
		return task;
	}

	
	@Override
	public synchronized boolean delete(String uuid) {
		if (!map.containsKey(uuid)){
			throw new NotFoundException();
		}
		
		map.remove(uuid);
		
		return true;
	}

}
