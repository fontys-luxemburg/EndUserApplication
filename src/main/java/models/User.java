package models;

public class User {
	private Role role;
	private String name;
	private String email;
	private String id;
	private String password;
	//Maybe more
	public User() {
	}
	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}



	public Role getRole() {
		return role;
	}

	public String getId() {
		return id;
	}

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}
}
