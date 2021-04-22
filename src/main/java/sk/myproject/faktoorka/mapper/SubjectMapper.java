package sk.myproject.faktoorka.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.api.model.Purchaser;
import sk.myproject.faktoorka.api.model.Sender;
import sk.myproject.faktoorka.api.model.SubjectReq;
import sk.myproject.faktoorka.api.model.SubjectRes;
import sk.myproject.faktoorka.entities.Subject;

@Component
@RequiredArgsConstructor
@SuppressWarnings("all")
public class SubjectMapper {

    private final AddressMapper addressMapper;
    private final PersonalInfoMapper personalInfoMapper;

    public Subject toSubjectEntity(SubjectReq subjectReq) {
        Subject subject = new Subject();
        subject.setName(subjectReq.getName());
        subject.setDic(subjectReq.getDic());
        subject.setIcDph(subjectReq.getIcDph());
        subject.setIco(subjectReq.getIco());
        subject.setIsVatPurchaser(subjectReq.getIsDphPurchaser());
        subject.setAddress(addressMapper.toEntityAddress(subjectReq.getAddress()));
        subject.setPersonalInfo(personalInfoMapper.toEntityPersonalInfo(subjectReq.getPersonalInfo()));

        return subject;
    }

    Subject toSubjectEntity(Purchaser purchaser) {
        Subject subject = new Subject();
        subject.setName(purchaser.getName());
        subject.setDic(purchaser.getDic());
        subject.setIcDph(purchaser.getIcDph());
        subject.setIco(purchaser.getIco());
        subject.setIsVatPurchaser(purchaser.getIsDphPurchaser());
        subject.setAddress(addressMapper.toEntityAddress(purchaser.getAddress()));
        subject.setPersonalInfo(personalInfoMapper.toEntityPersonalInfo(purchaser.getPersonalInfo()));

        return subject;
    }

    Subject toSubjectEntity(Sender sender) {
        Subject subject = new Subject();
        subject.setName(sender.getName());
        subject.setDic(sender.getDic());
        subject.setIcDph(sender.getIcDph());
        subject.setIco(sender.getIco());
        subject.setIsVatPurchaser(sender.getIsDphPurchaser());
        subject.setAddress(addressMapper.toEntityAddress(sender.getAddress()));
        subject.setPersonalInfo(personalInfoMapper.toEntityPersonalInfo(sender.getPersonalInfo()));

        return subject;
    }

    public Purchaser toPurchaser(Subject subject) {
        Purchaser purchaser = new Purchaser();
        purchaser.setName(subject.getName());
        purchaser.setDic(subject.getDic());
        purchaser.setIcDph(subject.getIcDph());
        purchaser.setIco(subject.getIco());
        purchaser.setId(subject.getId());
        purchaser.setIsDphPurchaser(subject.getIsVatPurchaser());
        purchaser.setAddress(addressMapper.fromEntityAddress(subject.getAddress()));
        purchaser.setPersonalInfo(personalInfoMapper.fromEntityPersonalInfo(subject.getPersonalInfo()));

        return purchaser;
    }

    public Sender toSender(Subject subject) {
        Sender sender = new Sender();
        sender.setName(subject.getName());
        sender.setDic(subject.getDic());
        sender.setIcDph(subject.getIcDph());
        sender.setIco(subject.getIco());
        sender.setId(subject.getId());
        sender.setIsDphPurchaser(subject.getIsVatPurchaser());
        sender.setAddress(addressMapper.fromEntityAddress(subject.getAddress()));
        sender.setPersonalInfo(personalInfoMapper.fromEntityPersonalInfo(subject.getPersonalInfo()));

        return sender;
    }
    
    public SubjectRes toSubjectRes(Subject subject) {
        SubjectRes subjectRes = new SubjectRes();
        subjectRes.setName(subject.getName());
        subjectRes.setDic(subject.getDic());
        subjectRes.setIcDph(subject.getIcDph());
        subjectRes.setIco(subject.getIco());
        subjectRes.setId(subject.getId());
        subjectRes.setIsDphPurchaser(subject.getIsVatPurchaser());
        subjectRes.setAddress(addressMapper.fromEntityAddress(subject.getAddress()));
        subjectRes.setPersonalInfo(personalInfoMapper.fromEntityPersonalInfo(subject.getPersonalInfo()));

        return subjectRes;
    }
}
