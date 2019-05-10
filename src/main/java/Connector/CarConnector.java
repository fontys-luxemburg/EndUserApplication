package Connector;

import models.Car;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarConnector {

	public List<Car> getAllCars(String userId){
		//Call the Government
		return new ArrayList<>();
	}
	public Optional<Car> getCarId(String carId){
		//Call the Government
		return Optional.of(new Car());
	}
}
