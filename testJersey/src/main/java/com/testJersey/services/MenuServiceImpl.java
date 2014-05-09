package com.testJersey.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.testJersey.entities.Menu;
import com.testJersey.interfaces.MenuService;



@Component
public class MenuServiceImpl implements MenuService {

	@Autowired
	private Util util;

	@Autowired
	private ApplicationContext appContext;



	@Override
	public Menu getMenu(int id){
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		Menu menu = new Menu();
		menu.setId(1);
		menu.setName(util.formatName("test name"));
		return menu;
	}

	@Override
	public List<Menu> getAllMenus(){
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menu = new Menu();
		menu.setId(1);
		menu.setName(util.formatName("test name "));
		menuList.add(menu);

		Menu menu2 = new Menu();
		menu2.setId(2);
		menu2.setName(util.formatName("test name 2 "));
		menuList.add(menu2);

		return menuList;
	}

}
