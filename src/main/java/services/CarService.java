package services;

import Connector.CarConnector;
import models.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarService{

	@Inject
	CarConnector carConnector;

	public Optional<Car> findById(String carId) {
		return Optional.empty();
	}


	public List<Car> findAll(String userId)
	{
		return carConnector.getAllCars(userId);
	}
}
