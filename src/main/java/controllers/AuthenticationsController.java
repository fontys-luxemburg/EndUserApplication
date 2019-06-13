package controllers;


import models.AuthResponse;
import models.Credentials;
import services.JwtService;


import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/authentication")
@Produces("application/json")
@Consumes("application/json")
public class AuthenticationsController {


	@Inject
	JwtService jwt;

	@POST
	public Response login(Credentials credentials) {
		try {
			return Response.ok(jwt.issueToken(credentials)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getLocalizedMessage()).build();
		}
	}


}