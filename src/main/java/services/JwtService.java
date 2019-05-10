package services;

import Connector.UserConnector;
import annotation.PropertiesFromFile;
import annotation.Secured;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
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
	@PropertiesFromFile
	Properties props;
	@PostConstruct
	public void initialize() {
		algorithm = Algorithm.HMAC256(props.getProperty("secret_key"));
		ISSUER = "endUserAPI";
	}

	public AuthResponse issueToken(Credentials credentials) throws Exception {
		//TODO Implement the connector class.
		User user = authenticate(credentials);


		return new AuthResponse(user,JWT.create()
				.withIssuer(ISSUER)
				.withClaim("Email",user.getEmail()).withClaim("Role",user.getRole().toString()).withIssuedAt(new Date())
				.sign(algorithm));
	}

	private User authenticate(Credentials credentials) throws Exception {
		Optional<User> user = userConnector.findByEmail(credentials.getEmail());

		if (!user.isPresent()) {
			throw new Exception("Username or Password incorrect");
		}

		if (!encodeSHA256(credentials.getPassword()).equals(user.get().getPassword())) {
			throw new Exception("Username or Password incorrect");
		}

		return user.get();
	}
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the Authorization header from the request
		String authorizationHeader =
				requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		// Validate the Authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}
		// Extract the token from the Authorization header
		String token = authorizationHeader
				.substring(AUTHENTICATION_SCHEME.length()).trim();
		try {
			// Validate the token
			validateToken(token);
		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
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
		// Check if the token was issued by the server and if it's not expired
		// Throw an Exception if the token is invalid
		Algorithm algorithm = Algorithm.HMAC256(props.getProperty("secret_key"));
		JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
				.withIssuer(ISSUER)
				.build(); //Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		Method method = resourceInfo.getResourceMethod();
		if (method != null) {
			String roles = jwt.getClaim("Role").asString();
			Role roleUser = Role.valueOf(roles);
			List<Role> methodRoles = extractRoles(method);
			if (!methodRoles.contains(roleUser)) {
				throw new Exception("no valid Role.");
			}
		}
	}
	private List<Role> extractRoles(AnnotatedElement annotatedElement) {
		if (annotatedElement == null) {
			return new ArrayList<>();
		} else {
			Secured secured = annotatedElement.getAnnotation(Secured.class);
			if (secured == null) {
				return new ArrayList<>();
			} else {
				Role[] allowedRoles = secured.value();
				return Arrays.asList(allowedRoles);
			}
		}
	}
	private static String encodeSHA256(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(hash);
	}
}
