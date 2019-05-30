package Connector;

import models.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarConnector {


	@Inject
	HTTPConnector httpConnector;

	public List<Car> getAllCars(String userId) throws Exception {
		//Call the Government
		HttpURLConnection http = httpConnector.createConnection("api/car/iets");
		http.getResponseMessage();
		return new ArrayList<>();
	}

	public Optional<Car> getCarId(String carId) {
		//Call the Government
		return Optional.of(new Car());
	}
}
