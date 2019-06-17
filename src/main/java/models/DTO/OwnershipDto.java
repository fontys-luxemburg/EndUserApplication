package models.DTO;

import java.io.Serializable;
import java.util.Date;

public class OwnershipDto implements Serializable {
	private long userID;
	private String userName;
	private long vehicleID;
	private String vehicleRegistrationID;
	private Long endDate;
	private Long createdAt;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getVehicleRegistrationID() {
		return vehicleRegistrationID;
	}

	public void setVehicleRegistrationID(String vehicleRegistrationID) {
		this.vehicleRegistrationID = vehicleRegistrationID;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
}

