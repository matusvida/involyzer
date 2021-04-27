package sk.myproject.faktoorka.mapper;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.InvoiceRes;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.errorhandling.InvoException;
import sk.myproject.faktoorka.pdf.creator.InvoiceCreator;
import sk.myproject.faktoorka.utils.InvoiceUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {

	private final SubjectMapper subjectMapper;
	private final ServiceMapper serviceMapper;
	private final InvoiceCreator invoiceCreator;

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

		invoice.setVatTotal(InvoiceUtils.calculateTotalVat(invoice.getServices()));
		invoice.setTotalExclVat(InvoiceUtils.calculateTotalPriceExtVat(invoice.getServices()));
		invoice.setTotal(InvoiceUtils.calculateTotalPriceWithVat(invoice.getServices()));

		invoice.setPurchaser(subjectMapper.toSubjectEntity(req.getPurchaser()));
		invoice.setSender(subjectMapper.toSubjectEntity(req.getSender()));

		try {
			invoice.setPdfInvoice(invoiceCreator.createInvoicePdf(invoice));
		} catch (DocumentException | InvoException e) {
			e.printStackTrace(); //todo
		}

		return invoice;
	}

	public InvoiceRes toInvoiceRes(Invoice invoice) {
		InvoiceRes invoiceRes = new InvoiceRes();

		invoiceRes.setId(invoice.getId());
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
