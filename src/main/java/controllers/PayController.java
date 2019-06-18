package controllers;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/Payment")
@Consumes("application/json")
@Produces("application/json")
public class PayController {
	@POST
	public Response pay(Long id){
		return Response.ok().build();
	}
}
