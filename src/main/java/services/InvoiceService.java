package services;

import Connector.InvoiceConnector;
import models.Invoice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class InvoiceService {
	@Inject
	InvoiceConnector invoiceConnector;

	public Invoice getId(int id){
		return invoiceConnector.getInvoice(id);
	}
	public List<Invoice> getAll(Long userId) throws Exception{
		return invoiceConnector.getAllInvoices(userId);
	}
	public Invoice payInvoice(Long userId)throws Exception{
		return invoiceConnector.payInvoice(userId,2019,6);
	}
}
