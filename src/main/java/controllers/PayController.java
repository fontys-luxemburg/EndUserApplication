package controllers;

import services.InvoiceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/Payment")
@Consumes("application/json")
@Produces("application/json")
public class PayController {
	@Inject
	InvoiceService invoiceService;
	@POST

	public Response pay(@QueryParam("user_id")String user_id,@QueryParam("year") int year, @QueryParam("month") int month) throws Exception{
		return Response.ok(invoiceService.payInvoice(Long.valueOf(user_id),2019,6)).build();
	}
}
