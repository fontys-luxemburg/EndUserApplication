package controllers;

import models.Invoice;
import services.InvoiceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/invoice")
@Produces("application/json")
@Consumes("application/json")
public class InvoiceController {

	@Inject
	InvoiceService invoiceService;

	@GET
	@Path("/betweenDate/{user_id}")
	public Response getInvoice(@PathParam("user_id") Long user_id, @QueryParam("year") int year, @QueryParam("month") int month)throws Exception{
		
		return Response.ok().build();
	}
	@GET
	@Path("/{id}")
	public Response getInvoiceUserId(@PathParam("id")int id)throws Exception{
		System.out.println("Current id:"+id);
		List<Invoice> invoiceList= invoiceService.getAll((long) id);

		if(invoiceList.size()==0)return Response.status(Response.Status.NO_CONTENT).build();
		return Response.ok(invoiceList).build();
	}
}
