package sk.myproject.faktoorka.utils;

import lombok.experimental.UtilityClass;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.api.model.Service;
import sk.myproject.faktoorka.entities.Invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@UtilityClass
public class InvoiceUtils {


    /**
     * Count total salary without/with VAT
     *
     * @param invoice {@link Invoice} entity.
     * @param req     {@link InvoiceReq} request object.
     */
    public void countTotal(Invoice invoice, InvoiceReq req) {
        if ((req.getVat() != null && req.getVat() > 0)) {
            // PricePerUnit * Quantity
            invoice.setTotalExclVat(BigDecimal.valueOf(req.getPricePerUnit() * req.getQuantity()));
            // WithoutVAT + (WithoutVAT * VAT/100)
            invoice.setTotal(invoice.getTotalExclVat()
                    .add(invoice.getTotalExclVat()
                            .multiply(BigDecimal.valueOf(req.getVat())
                                    .divide(BigDecimal.valueOf(100), 1, RoundingMode.HALF_UP))));
        } else {
            invoice.setTotal(BigDecimal.valueOf(req.getPricePerUnit() * (req.getQuantity())));
            invoice.setTotalExclVat(BigDecimal.valueOf(req.getPricePerUnit() * (req.getQuantity())));
        }
    }

    public void calculateTotalPrice(Set<Service> services) {

    }
}
