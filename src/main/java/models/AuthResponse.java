package models;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	private String id;
	private String name;
	private String email;
	private String token;

	public AuthResponse(User user, String token) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
