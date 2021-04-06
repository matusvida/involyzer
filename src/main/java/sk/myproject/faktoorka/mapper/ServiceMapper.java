package sk.myproject.faktoorka.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.entities.Service;

@Component
@RequiredArgsConstructor
public class ServiceMapper {

	public Service toEntityService(sk.myproject.faktoorka.api.model.Service requestService) {
		Service entityService = new Service();
		entityService.setPricePerUnit(requestService.getPricePerUnit());
		entityService.setServiceName(requestService.getServiceName());
		entityService.setQuantity(requestService.getQuantity());
		entityService.setTotalExclVat(requestService.getTotalExclVat());
		entityService.setTotalWithVat(requestService.getTotalWithVat());
		entityService.setVat(requestService.getVat());
		entityService.setUnit(requestService.getUnit());

		return entityService;
	}

	public sk.myproject.faktoorka.api.model.Service toModelService(Service entityService) {
		sk.myproject.faktoorka.api.model.Service service = new sk.myproject.faktoorka.api.model.Service();
		service.setId(entityService.getId());
		service.setPricePerUnit(entityService.getPricePerUnit());
		service.setQuantity(entityService.getQuantity());
		service.setServiceName(entityService.getServiceName());
		service.setTotalExclVat(entityService.getTotalExclVat());
		service.setTotalWithVat(entityService.getTotalWithVat());
		service.setUnit(entityService.getUnit());
		service.setVat(entityService.getVat());

		return service;
	}


}
