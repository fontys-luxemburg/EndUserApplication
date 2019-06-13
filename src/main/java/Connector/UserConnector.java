package Connector;

import com.google.gson.Gson;
import models.AuthResponse;
import models.Credentials;
import models.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

@ApplicationScoped
public class UserConnector {
	Gson gson = new Gson();

	@Inject
	HTTPConnector httpConnector;

	public Optional<User> findByEmail(String email) throws Exception {
		//Call the Government to get User
		return Optional.of(new User());
	}

	public String login(Credentials c) {
		try {
			String baseURI = "http://178.62.217.247";
			int port = 9060;
			String basePath = "/government/api/authentication";
			HttpPost postForum = new HttpPost(baseURI + ":" + port + basePath);
			HttpClient client = HttpClientBuilder.create().build();

			StringEntity requestEntity = new StringEntity(
					gson.toJson(c),
					ContentType.APPLICATION_JSON);
			postForum.setEntity(requestEntity);
			HttpResponse response = client.execute(postForum);


			return EntityUtils.toString(response.getEntity());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
