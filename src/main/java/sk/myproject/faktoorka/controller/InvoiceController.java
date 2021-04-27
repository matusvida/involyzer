package sk.myproject.faktoorka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import sk.myproject.faktoorka.api.InvoiceApi;
import sk.myproject.faktoorka.api.InvoicesApi;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.InvoiceRes;
import sk.myproject.faktoorka.errorhandling.InvoException;
import sk.myproject.faktoorka.service.InvoiceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class InvoiceController implements InvoiceApi, InvoicesApi {

    private final InvoiceService invoiceService;

    @Override
    @RequestMapping("invoice")
    public ResponseEntity<Void> createInvoice(@Valid InvoiceReq invoiceReq) {
        invoiceService.createInvoice(invoiceReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<InvoiceRes> getInvoiceById(Long id) {
        try {
            return new ResponseEntity<>(invoiceService.getInvoice(id.toString()), HttpStatus.OK);
        } catch (InvoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<InvoiceRes>> getInvoiceList() {
        return new ResponseEntity<>(invoiceService.getInvoices(), HttpStatus.OK);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }
}
