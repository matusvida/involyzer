package sk.myproject.faktoorka.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.entities.Service;
import sk.myproject.faktoorka.utils.InvoiceUtils;

@Component
@RequiredArgsConstructor
public class ServiceMapper {

	public Service toEntityService(sk.myproject.faktoorka.api.model.ServiceReq requestService) {
		Service entityService = new Service();
		entityService.setPricePerUnit(requestService.getPricePerUnit());
		entityService.setServiceName(requestService.getServiceName());
		entityService.setQuantity(requestService.getQuantity());
		InvoiceUtils.countTotalForService(entityService, requestService);
		entityService.setVat(requestService.getVat());
		entityService.setUnit(requestService.getUnit());

		return entityService;
	}

	public sk.myproject.faktoorka.api.model.ServiceRes toModelService(Service entityService) {
		sk.myproject.faktoorka.api.model.ServiceRes service = new sk.myproject.faktoorka.api.model.ServiceRes();
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
