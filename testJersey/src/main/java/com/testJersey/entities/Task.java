package com.testJersey.entities;

import java.util.UUID;

public class Task {

	private String uuid;
	private String title;
	private String description; 
	private boolean completed;
		
	
	public Task(String uuid, String title, String description, boolean completed) {
		this.title = title;
		this.description = description;
		this.completed = completed;
		this.uuid = uuid;
		
	}

	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
	
}
