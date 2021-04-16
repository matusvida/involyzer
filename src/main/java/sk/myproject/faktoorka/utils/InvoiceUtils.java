package sk.myproject.faktoorka.utils;

import lombok.experimental.UtilityClass;
import sk.myproject.faktoorka.api.model.InvoiceReq;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.entities.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@UtilityClass
public class InvoiceUtils {


	/**
	 * Count total salary without/with VAT
	 *
	 * @param service {@link Invoice} entity.
	 * @param serviceRequest     {@link InvoiceReq} request object.
	 */
	public void countTotalForService(Service service, sk.myproject.faktoorka.api.model.ServiceReq serviceRequest) {
		if ((serviceRequest.getVat() != null && serviceRequest.getVat().compareTo(BigDecimal.ZERO) > 0)) {
			// PricePerUnit * Quantity
			service.setTotalExclVat(serviceRequest.getPricePerUnit().multiply(serviceRequest.getQuantity()));
			// WithoutVAT + (WithoutVAT * VAT/100)
			service.setTotalWithVat(service.getTotalExclVat()
				.add(service.getTotalExclVat()
					.multiply(serviceRequest.getVat()
						.divide(BigDecimal.valueOf(100), 1, RoundingMode.HALF_UP))));
		} else {
			service.setTotalWithVat(serviceRequest.getPricePerUnit().multiply(serviceRequest.getQuantity()));
			service.setTotalExclVat(serviceRequest.getPricePerUnit().multiply(serviceRequest.getQuantity()));
		}
	}

	public BigDecimal calculateTotalVat(Set<Service> services) {
		return services
			.stream()
			.filter(service -> service.getVat().compareTo(BigDecimal.ZERO) > 0)
			.map(service -> service.getTotalExclVat().multiply(service.getVat().divide(BigDecimal.valueOf(100), 1, RoundingMode.HALF_UP)))
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calculateTotalPriceWithVat(Set<Service> services) {
		return services
			.stream()
			.map(sk.myproject.faktoorka.entities.Service::getTotalWithVat)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calculateTotalPriceExtVat(Set<Service> services) {
		return services
			.stream()
			.map(Service::getTotalExclVat)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
