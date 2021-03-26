package sk.myproject.faktoorka.service;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.InvoiceRes;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.errorhandling.InvoException;
import sk.myproject.faktoorka.mapper.InvoiceMapper;
import sk.myproject.faktoorka.pdf.creator.InvoiceCreator;
import sk.myproject.faktoorka.repositories.InvoiceRepo;

import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceCreator invoiceCreator;

    public InvoiceRes getInvoice(String id) throws InvoException {
        Invoice invoice = invoiceRepo.findById(Long.valueOf(id)).orElse(null);
        log.info("invoice:", invoice);
        if (invoice == null) {
            throw new InvoException(MessageFormat.format("Invoice with id: {} is null", id));
        }
        return invoiceMapper.toInvoiceRes(invoice);
    }

    public List<InvoiceRes> getInvoices() {
        List<Invoice> invoices = invoiceRepo.findAll();
        return invoices
                .parallelStream()
                .map(invoiceMapper::toInvoiceRes)
                .collect(Collectors.toList());
    }

    public void createInvoice(InvoiceReq request) {
        Invoice invoice = invoiceMapper.toInvoice(request);
        try {
            invoiceCreator.createInvoicePdf(invoice);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        invoiceRepo.saveAndFlush(invoice);
    }
}
