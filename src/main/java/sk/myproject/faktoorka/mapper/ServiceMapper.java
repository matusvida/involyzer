package sk.myproject.faktoorka.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.entities.Service;

@Component
@RequiredArgsConstructor
public class ServiceMapper {

	public Service toService(sk.myproject.faktoorka.api.model.Service requestService) {
		Service entityService = new Service();
		entityService.setPricePerUnit(requestService.getPricePerUnit());
		entityService.setServiceName(requestService.getServiceName());
		entityService.setQuantity(requestService.getQuantity());
		entityService.setTotalExclVat(requestService.getTotalExclVat());
		entityService.setTotalWithVat(requestService.getTotalWithVat());
		entityService.setVat(requestService.getVat());
		entityService.getUnit(requestService.getUnit())

		return entityService;
	}


}
