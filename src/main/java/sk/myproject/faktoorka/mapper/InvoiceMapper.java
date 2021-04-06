package sk.myproject.faktoorka.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.InvoiceRes;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.utils.InvoiceUtils;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {

    private final SubjectMapper subjectMapper;
    private final ServiceMapper serviceMapper;

    public Invoice toInvoice(InvoiceReq req) {
        Invoice invoice = new Invoice();

        invoice.setName(req.getName());
        invoice.setDueDate(Date.valueOf(req.getDueDate()));
        invoice.setIssueDate(Date.valueOf(req.getIssueDate()));
        invoice.setMonth(Date.valueOf(req.getMonth()));
//        invoice.setPricePerUnit(req.getPricePerUnit());
//        invoice.setQuantity(req.getQuantity());
//        invoice.setService(req.getService());
//
//        invoice.setUnit(req.getUnit());
//        invoice.setVat(req.getVat());

        invoice.setPurchaser(subjectMapper.toSubjectEntity(req.getPurchaser()));
        invoice.setSender(subjectMapper.toSubjectEntity(req.getSender()));

        InvoiceUtils.countTotal(invoice, req);

        return invoice;
    }

    public InvoiceRes toInvoiceRes(Invoice invoice) {
        InvoiceRes invoiceRes = new InvoiceRes();

        invoiceRes.setName(invoice.getName());
        invoiceRes.setDueDate(LocalDate.parse(invoice.getDueDate().toString()));
        invoiceRes.setMonth(LocalDate.parse(invoice.getMonth().toString()));
        invoiceRes.setIssueDate(LocalDate.parse(invoice.getIssueDate().toString()));
//        invoiceRes.setPricePerUnit(invoice.getPricePerUnit());
//        invoiceRes.setQuantity(invoice.getQuantity());
//        invoiceRes.setService(invoice.getService());
//        invoiceRes.setTotal(invoice.getTotal().longValue());
//        invoiceRes.setTotalExcVat(invoice.getTotalExclVat().longValue());
//        invoiceRes.setUnit(invoice.getUnit());
//        invoiceRes.setVat(invoice.getVat());
        invoiceRes.setPurchaser(subjectMapper.toPurchaser(invoice.getPurchaser()));
        invoiceRes.setSender(subjectMapper.toSender(invoice.getSender()));

        return invoiceRes;
    }




}
