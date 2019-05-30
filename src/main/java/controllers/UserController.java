package controllers;

import models.Credentials;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces("application/json")
@Consumes("application/json")
public class UserController {

	@Inject
	UserService userService;

	@GET
	public Response test()  {
		Credentials c = new Credentials();
		c.setEmail("johndoe@test.com");
		c.setPassword("testpassword");

		return Response.ok(userService.login(c)).build();
	}
}
