package sk.myproject.faktoorka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sk.myproject.faktoorka.api.InvoicesApi;
import sk.myproject.faktoorka.errorhandling.InvoException;
import sk.myproject.faktoorka.service.InvoiceService;

@RestController
@RequiredArgsConstructor
public class InvoicePdfController implements InvoicesApi {

	private final InvoiceService invoiceService;

	@Override
	public ResponseEntity<Resource> getInvoicePDFById(Long id) {
		try {
			return new ResponseEntity<>(invoiceService.getPdfInvoice(id.toString()), HttpStatus.OK);
		} catch (InvoException e) {
			e.printStackTrace();
		}
		return null;
	}
}
