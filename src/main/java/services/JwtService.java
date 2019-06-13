package services;

import Connector.UserConnector;
import annotation.PropertiesFromFile;
import annotation.Secured;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import models.AuthResponse;
import models.Credentials;
import models.Role;
import models.User;


import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class JwtService implements ContainerRequestFilter {

	@Inject
	UserConnector userConnector;
	@Context
	private ResourceInfo resourceInfo;
	private static final String AUTHENTICATION_SCHEME = "Bearer";
	private static final String REALM = "example";
	private Algorithm algorithm;
	private String ISSUER;

	@Inject
	@PropertiesFromFile
	Properties props;

	@PostConstruct
	public void initialize() {
		System.out.println("Starting....."+ props.getProperty("secret_key"));
		algorithm = Algorithm.HMAC256(props.getProperty("secret_key"));
		System.out.println(algorithm);
		ISSUER = "endUserAPI";
		System.out.println("Done!");
	}

	public AuthResponse issueToken(Credentials credentials) throws Exception {
		String govString = userConnector.login(credentials);

		AuthResponse tokenGovernment = new Gson().fromJson(govString, AuthResponse.class);
		if(tokenGovernment.getEmail() ==null)throw new Exception("Email and/or Password is wrong!");
		User user = decodeJWT(tokenGovernment);
		return new AuthResponse(user,JWT.create()
				.withIssuer(ISSUER)
				.withClaim("Email",user.getEmail()).withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() +3600 * 1000))
				.sign(algorithm));
	}
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader =
				requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}
		String token = authorizationHeader
				.substring(AUTHENTICATION_SCHEME.length()).trim();
		try {
			validateToken(token);
		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		return authorizationHeader != null && authorizationHeader.toLowerCase()
				.startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(
				Response.status(Response.Status.UNAUTHORIZED)
						.header(HttpHeaders.WWW_AUTHENTICATE,
								AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
						.build());
	}

	private void validateToken(String token) throws Exception {
		JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
				.withIssuer(ISSUER)
				.build(); //Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		if(jwt.getExpiresAt().before(new Date()))throw new Exception("Token expired!");
	}

	private User decodeJWT(AuthResponse a){
		User u = new User();
		Algorithm algorithm = Algorithm.HMAC256(props.getProperty("secret_key2"));
		JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
				.withIssuer("government_api")
				.build();
		DecodedJWT jwt = verifier.verify(a.getToken());
		u.setEmail(jwt.getSubject());
		u.setName(a.getName());
		u.setId(a.getId());
		return u;
	}
}
