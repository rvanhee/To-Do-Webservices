package com.testJersey.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.testJersey.entities.Menu;
import com.testJersey.interfaces.MenuService;



@Path("/json/menu")
public class MenuWS {

	@Autowired
	private MenuService menu;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Menu getMenuWS(@PathParam("id") int id) throws Exception{
		return menu.getMenu(id);	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	public List<Menu> getAllMenusWS() throws Exception{
		return menu.getAllMenus();	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Menu menu) {
		if (menu == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		menu.setName(menu.getName() + " inserted");
		
		return Response.status(Status.CREATED).entity(menu).build();
		
		//return Response.created(UriBuilder.fromPath("/json/menu/" + "1").build()).build();
		//return Response.created(UriBuilder.fromPath("/" + entity.getId()).build()).build();
	}
	
}
