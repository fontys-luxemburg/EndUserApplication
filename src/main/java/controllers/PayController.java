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
	@Path("{id}")
	public Response pay(@PathParam("id") Long id) throws Exception{

		return Response.ok(invoiceService.payInvoice(id,2019,6)).build();
	}
}
