package Connector;

import models.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserConnector {

	public Optional<User> findByEmail(String email){
		//Call the Government to get User
		return Optional.of(new User());
	}
}
