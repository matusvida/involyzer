package sk.myproject.involyzer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.myproject.involyzer.model.InvoiceDto;
import sk.myproject.involyzer.repositories.InvoiceRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;

    public InvoiceDto getInvoice(String id) {
        InvoiceDto invoiceDto = invoiceRepo.findById(Long.valueOf(id)).orElse(null);
        log.info("invoice:", invoiceDto);
        return invoiceDto;
    }

    public List<InvoiceDto> getInvoices() {
        return invoiceRepo.findAll();
    }
}
