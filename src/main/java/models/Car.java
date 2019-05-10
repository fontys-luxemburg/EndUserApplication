package models;

public class Car {
	private String registrationID;
	private String brand;
	private String bodyType ;
	private String model;


	public Car(){}

	public String getRegistrationID() {
		return registrationID;
	}

	public Car setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
		return this;
	}

	public String getBrand() {
		return brand;
	}

	public Car setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public String getBodyType() {
		return bodyType;
	}

	public Car setBodyType(String bodyType) {
		this.bodyType = bodyType;
		return this;
	}

	public String getModel() {
		return model;
	}

	public Car setModel(String model) {
		this.model = model;
		return this;
	}
}
