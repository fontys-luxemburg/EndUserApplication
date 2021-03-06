package controllers;

import models.Car;
import services.CarService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.awt.geom.Point2D;
import java.util.List;

@Path("/car")
@Produces("application/json")
@Consumes("application/json")
public class CarController {

	@Inject
	CarService carService;

	@GET
	@Path("/{id}")
	public Response getCars(@PathParam(value = "id")String id) throws Exception {
		List<Car> carList = carService.findAll(id);
		return Response.ok(carList).build();
	}

	@GET
	@Path("/test")public Response test(){
		final int R = 6371;

		double a = (Point2D.distance(51.562820,5.084620,51.563299,5.090967)*R);

		return Response.ok(a).build();
	}
}
