package sk.myproject.faktoorka.mapper;

import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.api.model.Address;

@Component
public class AddressMapper {

    sk.myproject.faktoorka.entities.Address toEntityAddress(Address address) {
        sk.myproject.faktoorka.entities.Address entityAddress = new sk.myproject.faktoorka.entities.Address();
        entityAddress.setStreet(address.getStreet());
        entityAddress.setHouseNumber(address.getHouseNumber());
        entityAddress.setPostalNumber(address.getPostalNumber());
        entityAddress.setCity(address.getCity());
        entityAddress.setCountry(address.getCountry());

        return entityAddress;
    }

    Address fromEntityAddress(sk.myproject.faktoorka.entities.Address address) {
        Address modelAddress = new Address();
        modelAddress.setId(address.getId());
        modelAddress.setStreet(address.getStreet());
        modelAddress.setHouseNumber(address.getHouseNumber());
        modelAddress.setPostalNumber(address.getPostalNumber());
        modelAddress.setCity(address.getCity());
        modelAddress.setCountry(address.getCountry());

        return modelAddress;
    }
}
