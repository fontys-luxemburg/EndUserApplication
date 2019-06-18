package models;

import java.io.Serializable;

public class BaseModel implements Serializable {
	private Long id;

	public Long getId() {
		return id;
	}

	public BaseModel setId(Long id) {
		this.id = id;
		return this;
	}

}
