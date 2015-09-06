package com.testJersey.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testJersey.client.entities.Task;

public class TaskWSConsummer {


	private String url = "http://localhost:8080/testJersey/api/v1/tasks";
	
	public HttpClient client = new DefaultHttpClient();
	private Gson gson = new Gson();	

	
	public Task post(Task task) throws Exception{

		String jsonRepresentation = new Gson().toJson(task);	
		StringEntity se = new StringEntity(jsonRepresentation);

		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(se);
		
		
		Reader reader = executeRequest(httpPost);
		if (reader != null){
			task = gson.fromJson(reader,  Task.class);
			return task;
		} else {
			throw new Exception("reader is null");
		}

	}
	
	
	public Task put(Task task) throws Exception{

		String jsonRepresentation = new Gson().toJson(task);	
		StringEntity se = new StringEntity(jsonRepresentation);

		HttpPut httpPut = new HttpPut(url + "/" + task.getUuid());
		httpPut.addHeader("Content-Type", "application/json");
		httpPut.setEntity(se);
		
		
		Reader reader = executeRequest(httpPut);
		if (reader != null){
			task = gson.fromJson(reader,  Task.class);
			return task;
		} else {
			throw new Exception("reader is null");
		}

	}

	public void delete(String uuid) throws Exception{

		HttpPut httpPut = new HttpPut(url + "/" + uuid);
		httpPut.addHeader("Content-Type", "application/json");
		
		executeRequest(httpPut);
	}


	public Task get(String uuid) throws Exception{

		HttpGet httpGet = new HttpGet(url + "/" + uuid);
		
		Reader reader = executeRequest(httpGet);

		if (reader != null){
			Gson gson = new Gson();			
			return gson.fromJson(reader,  Task.class);
		} else {
			throw new Exception("reader is null");
		}
	}


	public List<Task> getAll() throws Exception{
		
		HttpGet httpGet = new HttpGet(url);
		
		Reader reader = executeRequest(httpGet);

		if (reader != null){
			Gson gson = new Gson();		
			Type collectionType = new TypeToken<List<Task>>(){}.getType();
			List<Task> tasks = gson.fromJson(reader, collectionType);

			return tasks;
		} else {
			throw new Exception("reader is null");
		}
	}


	private Reader executeRequest(HttpRequestBase httpRequest) throws Exception{
		try {	
			HttpResponse response = client.execute(httpRequest);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200 || statusLine.getStatusCode() == 201) {
				HttpEntity entity = response.getEntity();
				InputStream inputStream = entity.getContent();
				Reader reader = new InputStreamReader(inputStream);
				return reader;
			} else if (statusLine.getStatusCode() == 204){
				return null;
			}else {
				throw new Exception(statusLine.getStatusCode() + "");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			httpRequest.releaseConnection();			
		}
	}

}
