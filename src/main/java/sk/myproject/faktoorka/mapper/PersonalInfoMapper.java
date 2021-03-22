package sk.myproject.faktoorka.mapper;

import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.api.model.PersonalInfo;

@Component
public class PersonalInfoMapper {

    sk.myproject.faktoorka.entities.PersonalInfo toEntityPersonalInfo(PersonalInfo personalInfo) {
        sk.myproject.faktoorka.entities.PersonalInfo entityPersonalInfo = new sk.myproject.faktoorka.entities.PersonalInfo();
        entityPersonalInfo.setAccountNumber(personalInfo.getAccountNumber());
        entityPersonalInfo.setBank(personalInfo.getBank());
        entityPersonalInfo.setEmail(personalInfo.getEmail());
        entityPersonalInfo.setIban(personalInfo.getIban());
        entityPersonalInfo.setPhone(personalInfo.getPhone());
        entityPersonalInfo.setWebsite(personalInfo.getWebsite());

        return entityPersonalInfo;
    }

    PersonalInfo fromEntityPersonalInfo(sk.myproject.faktoorka.entities.PersonalInfo personalInfo) {
        PersonalInfo modelPersonalInfo = new PersonalInfo();
        modelPersonalInfo.setId(personalInfo.getId());
        modelPersonalInfo.setAccountNumber(personalInfo.getAccountNumber());
        modelPersonalInfo.setBank(personalInfo.getBank());
        modelPersonalInfo.setEmail(personalInfo.getEmail());
        modelPersonalInfo.setIban(personalInfo.getIban());
        modelPersonalInfo.setPhone(personalInfo.getPhone());
        modelPersonalInfo.setWebsite(personalInfo.getWebsite());

        return modelPersonalInfo;
    }
}
