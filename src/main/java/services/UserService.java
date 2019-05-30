package services;

import Connector.UserConnector;
import models.Credentials;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService {
	@Inject
	UserConnector userConnector;
	public String login(Credentials c)  {
		return userConnector.login(c);
	}
}
