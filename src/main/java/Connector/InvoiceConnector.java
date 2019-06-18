package Connector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Invoice;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class InvoiceConnector {
	private final String baseURI = "http://178.62.217.247";
	private final int port = 9060;
	private final String basePath = "/government/api/invoices/user/";
	private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJq" +
			"b2huZG9lQHRlc3QuY29tIiwiaXNzIjoiZ292ZXJubWVudF9hcGkifQ.CG7PA_mWJvIK6bwLSH2kYj-puMRFGK9_zUHTpJ2OSXI";

	public Invoice getInvoice(int id) {

		return null;
	}

	public List<Invoice> getAllInvoices(Long userId) throws Exception {
		AtomicReference<HttpGet> getInvoices = new AtomicReference<>(new HttpGet(baseURI + ":" + port + basePath + userId.intValue() + "/all"));
		HttpClient client = HttpClientBuilder.create().build();
		getInvoices.get().setHeader("Authorization", "bearer " + token);
		HttpResponse response = client.execute(getInvoices.get());

		String x = EntityUtils.toString(response.getEntity());

		if (response.getStatusLine().getStatusCode() == 204 || response.getStatusLine().getStatusCode() == 404) {
			return new ArrayList<>();
		}
		TypeToken<List<Invoice>> token = new TypeToken<List<Invoice>>() {};
		Type listType = token.getType();
		Gson gson = new Gson();

		return gson.<ArrayList<Invoice>>fromJson(x, listType);
	}
}
