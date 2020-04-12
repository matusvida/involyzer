package sk.myproject.involyzer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.myproject.involyzer.model.InvoiceDto;
import sk.myproject.involyzer.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("invoice")
    public InvoiceDto getInvoice(@RequestParam String id) {
        return invoiceService.getInvoice(id);
    }

    @GetMapping("invoices")
    public List<InvoiceDto> getInvoices() {
        return invoiceService.getInvoices();
    }

}
