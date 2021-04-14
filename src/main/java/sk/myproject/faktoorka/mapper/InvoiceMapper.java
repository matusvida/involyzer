package sk.myproject.faktoorka.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.InvoiceRes;
import sk.myproject.faktoorka.api.model.Service;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.utils.InvoiceUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {

	private final SubjectMapper subjectMapper;
	private final ServiceMapper serviceMapper;

	public Invoice toEntityInvoice(InvoiceReq req) {
		Invoice invoice = new Invoice();

		invoice.setName(req.getName());
		invoice.setDueDate(Date.valueOf(req.getDueDate()));
		invoice.setIssueDate(Date.valueOf(req.getIssueDate()));
		invoice.setMonth(Date.valueOf(req.getMonth()));

		invoice.setServices(req.getServices()
			.parallelStream()
			.map(serviceMapper::toEntityService)
			.collect(Collectors.toSet()));

		invoice.setTotalExclVat(req.getServices()
			.stream()
			.map(service -> service.getTotalExclVat().multiply(service.getQuantity()))
			.reduce(BigDecimal.ZERO, BigDecimal::add));
		invoice.setTotal(invoice.getServices()
			.stream()
			.map(service -> service.getTotalWithVat()));
		invoice.setVatTotal(BigDecimal.ONE);
		InvoiceUtils.countTotalForService(invoice, req);

		invoice.setPurchaser(subjectMapper.toSubjectEntity(req.getPurchaser()));
		invoice.setSender(subjectMapper.toSubjectEntity(req.getSender()));

		return invoice;
	}

	public InvoiceRes toInvoiceRes(Invoice invoice) {
		InvoiceRes invoiceRes = new InvoiceRes();

		invoiceRes.setName(invoice.getName());
		invoiceRes.setDueDate(LocalDate.parse(invoice.getDueDate().toString()));
		invoiceRes.setMonth(LocalDate.parse(invoice.getMonth().toString()));
		invoiceRes.setIssueDate(LocalDate.parse(invoice.getIssueDate().toString()));

		invoiceRes.setServices(invoice.getServices()
			.parallelStream()
			.map(serviceMapper::toModelService)
			.collect(Collectors.toList()));
		invoiceRes.setTotal(invoice.getTotal());
		invoiceRes.setTotalExcVat(invoice.getTotalExclVat());
		invoiceRes.setVat(invoice.getVatTotal());
		invoiceRes.setPurchaser(subjectMapper.toPurchaser(invoice.getPurchaser()));
		invoiceRes.setSender(subjectMapper.toSender(invoice.getSender()));

		return invoiceRes;
	}


}
