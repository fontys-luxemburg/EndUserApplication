package Connector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Car;
import models.DTO.OwnershipDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.lang.reflect.Type;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class CarConnector {


	@Inject
	HTTPConnector httpConnector;


	public List<Car> getAllCars(String userId) throws Exception {
		//Call the Government
		String baseURI = "http://178.62.217.247";
		int port = 9060;
		String basePath = "/government/api/users/"+userId+"/ownerships";
		AtomicReference<HttpGet> getCar = new AtomicReference<>(new HttpGet(baseURI + ":" + port + basePath));
		HttpClient client = HttpClientBuilder.create().build();
		getCar.get().setHeader("Authorization","bearer "+ "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJq" +
				"b2huZG9lQHRlc3QuY29tIiwiaXNzIjoiZ292ZXJubWVudF9hcGkifQ.CG7PA_mWJvIK6bwLSH2kYj-puMRFGK9_zUHTpJ2OSXI");
		HttpResponse response = client.execute(getCar.get());
		TypeToken<List<OwnershipDto>> token = new TypeToken<List<OwnershipDto>>() {};
		Type listType = token.getType();
		Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG, DateFormat.LONG).create();
		ArrayList<OwnershipDto> list = gson.fromJson(EntityUtils.toString(response.getEntity()),listType);
		basePath = "/government/api/vehicles/";
		List<Car> carlist = new ArrayList<>();
		String finalBasePath = basePath;

		for (OwnershipDto o : list) {
			if(o.getEndDate()!= null)continue;;
			try {
				getCar.set(new HttpGet(baseURI + ":" + port + finalBasePath + o.getVehicleRegistrationID()));
				getCar.get().setHeader("Authorization", "bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
						"eyJzdWIiOiJqb2huZG9lQHRlc3QuY29tIiwiaXNzIjoiZ292ZXJubWVudF9hcGkifQ.C" +
						"G7PA_mWJvIK6bwLSH2kYj-puMRFGK9_zUHTpJ2OSXI");
				HttpResponse response2 = client.execute(getCar.get());
				carlist.add(gson.fromJson(EntityUtils.toString(response2.getEntity()), Car.class));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return carlist;
	}

	public Optional<Car> getCarId(String carId) {
		//Call the Government
		return Optional.of(new Car());
	}
}
