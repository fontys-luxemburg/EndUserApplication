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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
		AuthResponse authResponse = null;
		HttpURLConnection http = httpConnector.createConnection("api/authentication");
		try {
			http.setRequestMethod("POST");

			http.setRequestProperty("Content-Type", "application/json; utf-8");
			http.setRequestProperty("Accept", "application/json");
			http.setDoOutput(true);
			String jsonInputString = gson.toJson(c);
			http.setRequestProperty("body",jsonInputString);
			try (OutputStream os = http.getOutputStream()) {
				http.connect();
				//byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
				System.out.println(jsonInputString);
				//os.write(input);
				try (BufferedReader br = new BufferedReader(
						new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))) {
					StringBuilder response = new StringBuilder();
					String responseLine;
					while ((responseLine = br.readLine()) != null) {
						response.append(responseLine.trim());
					}
					System.out.println(response.toString());
					authResponse = gson.fromJson(response.toString(), AuthResponse.class);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return authResponse.getToken();
	}
}
