package com.testJersey.client;



import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testJersey.client.entities.Menu;


public class MenuWSConsummer {

	public HttpClient client = new DefaultHttpClient();

	private String url = "http://localhost:8080/testJersey/json/menu";


	public Menu getMenu(){

		HttpGet httpGet = new HttpGet(url + "/1");
		httpGet.addHeader("SM_USER", "test");

		Reader reader = get(httpGet);

		if (reader != null){
			Gson gson = new Gson();			
			Menu menu = gson.fromJson(reader,  Menu.class);

			return menu;
		} else {
			return null;
		}
	}

	public List<Menu> getAllMenu(){

		HttpGet httpGet = new HttpGet(url + "/getAll");
		httpGet.addHeader("SM_USER", "test");

		Reader reader = get(httpGet);

		if (reader != null){
			Gson gson = new Gson();		
			Type collectionType = new TypeToken<List<Menu>>(){}.getType();
			List<Menu> details = gson.fromJson(reader, collectionType);

			return details;
		} else {
			return null;
		}
	}
	
	
	public Menu postMenu(Menu menu){
		HttpPost httpPost = new HttpPost(url + "/");
		httpPost.addHeader("Content-Type", "application/json");
		
		String jsonRepresentation = new Gson().toJson(menu);	
		try{
			StringEntity se = new StringEntity(jsonRepresentation);
			
			httpPost.setEntity(se);

			Reader reader = post(httpPost);

			if (reader != null){
				Gson gson = new Gson();			
				menu = gson.fromJson(reader,  Menu.class);
				return menu;
			} 
		} catch (Exception e){
			System.out.println(e.getMessage());		
		}
		return null;
	}

	
	private Reader get(HttpGet httpGet){
		try{
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream inputStream = entity.getContent();
				Reader reader = new InputStreamReader(inputStream);
				return reader;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());		
		}
		return null;
	}
	
	
	private Reader post(HttpPost httpPost){
		try{
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 201) {
				HttpEntity entity = response.getEntity();
				InputStream inputStream = entity.getContent();
				Reader reader = new InputStreamReader(inputStream);
				return reader;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
