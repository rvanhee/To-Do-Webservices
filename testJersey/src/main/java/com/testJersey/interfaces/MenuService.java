package com.testJersey.interfaces;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.testJersey.entities.Menu;



public interface MenuService {

	@PreAuthorize("hasPermission(#id, 'hello')")
	public Menu getMenu(int id);
	
	@PreAuthorize("isAuthenticated() && hasRole('ROLE_ADMIN')")	
	public List<Menu> getAllMenus();
}
