package com.testJersey.webservices;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import com.testJersey.entities.Task;
import com.testJersey.interfaces.TaskServices;

@Path("/api/v1/tasks")
public class TaskWS {

	@Autowired
	private TaskServices taskServices;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{uuid}")
	public Task get(@PathParam("uuid") String uuid) throws Exception{
		return taskServices.get(uuid);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getAll() throws Exception{
		return taskServices.getAll();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Task task) throws Exception{
		if (task == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		taskServices.save(task);
		
		return Response.status(Status.CREATED).entity(task).build();
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{uuid}")
	public Response update(@PathParam("uuid") String uuid, Task task) throws Exception{
		if (task == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		task.setUuid(uuid);
		task = taskServices.update(task);
		
		return Response.status(Status.OK).entity(task).build();
	}
	
	
	@DELETE
	@Path("/{uuid}")
	public Response delete(@PathParam("uuid") String uuid) throws Exception{

		taskServices.delete(uuid);
		return Response.status(Status.NO_CONTENT).build();

	}



	
}
