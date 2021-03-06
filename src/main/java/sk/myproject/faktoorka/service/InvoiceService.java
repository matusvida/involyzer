package sk.myproject.faktoorka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.InvoiceRes;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.errorhandling.InvoException;
import sk.myproject.faktoorka.mapper.InvoiceMapper;
import sk.myproject.faktoorka.repositories.InvoiceRepo;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final InvoiceMapper invoiceMapper;

    public InvoiceRes getInvoice(String id) throws InvoException {
        Invoice invoice = invoiceRepo.findById(Long.valueOf(id)).orElse(null);
        log.info("invoice:", invoice);
        if (invoice == null) {
            throw new InvoException(MessageFormat.format("Invoice with id: {} is null", id));
        }
        return invoiceMapper.toInvoiceRes(invoice);
    }

    public Resource getPdfInvoice(String id) throws InvoException {
        Invoice invoice = invoiceRepo.findById(Long.valueOf(id)).orElse(null);
        if (invoice == null) {
            throw new InvoException(MessageFormat.format("Invoice with id: {} is null", id));
        }
        return new ByteArrayResource(invoice.getPdfInvoice());
    }

    public List<InvoiceRes> getInvoices() {
        List<Invoice> invoices = invoiceRepo.findAll();
        return invoices
                .parallelStream()
                .map(invoiceMapper::toInvoiceRes)
                .collect(Collectors.toList());
    }

    public void createInvoice(InvoiceReq request) {
        Invoice invoice = invoiceMapper.toEntityInvoice(request);
        invoiceRepo.saveAndFlush(invoice);
    }
}
