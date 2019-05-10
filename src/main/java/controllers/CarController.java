package controllers;

import models.Car;
import services.CarService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/car")
public class CarController {

	@Inject
	CarService carService;

	@GET
	@Path("/{id}")
	public Response getCars(@PathParam(value = "id")String id){
		List<Car> carList = carService.findAll(id);
		return Response.ok(carList).build();
	}
}
