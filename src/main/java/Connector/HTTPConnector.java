package Connector;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@ApplicationScoped
public class HTTPConnector {

	HttpURLConnection createConnection(String endpoint) {
		HttpURLConnection http =  null;
		try {
			URL url = new URL("http://localhost:8080/government/"+endpoint);
			URLConnection con = url.openConnection();
			http = (HttpURLConnection) con;
			http.setDoOutput(true);
			return http;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return http;
	}
}
