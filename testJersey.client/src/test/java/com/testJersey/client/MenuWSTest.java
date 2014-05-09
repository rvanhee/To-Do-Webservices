package com.testJersey.client;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.testJersey.client.entities.Menu;

import junit.framework.TestCase;

public class MenuWSTest extends TestCase {

	
	@Test
	public void testGetMenu(){
		MenuWSConsummer menuWSConsummer = new MenuWSConsummer();
		Menu menu = menuWSConsummer.getMenu();
		
		assertEquals(menu.getId(), 1);
	}
	
	@Test
	public void testGetAllMenu(){
		MenuWSConsummer menuWSConsummer = new MenuWSConsummer();
		List<Menu> menuList = menuWSConsummer.getAllMenu();
		
		assertEquals(menuList.get(0).getId(), 1);
		assertEquals(menuList.get(1).getId(), 2);
	}


	@Test
	public void testErrorCode(){
		HttpClient client = new DefaultHttpClient();
		String url = "http://localhost:8080/testJersey/json/menu";

		HttpGet httpGet = new HttpGet(url + "/getAll");
		try{
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			assertEquals(statusLine.getStatusCode(), 500);
		} catch (Exception e){

		}
	}
	
	
	@Test
	public void testPostMenu(){
		MenuWSConsummer menuWSConsummer = new MenuWSConsummer();
		
		Menu menu = new Menu();
		menu.setId(5);
		menu.setName("testPost");
		
		menu = menuWSConsummer.postMenu(menu);
		assertEquals(menu.getId(), 5);
	}

}
