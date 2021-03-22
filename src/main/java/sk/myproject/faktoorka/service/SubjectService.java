package sk.myproject.faktoorka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sk.myproject.faktoorka.api.model.SubjectReq;
import sk.myproject.faktoorka.api.model.SubjectRes;
import sk.myproject.faktoorka.entities.Subject;
import sk.myproject.faktoorka.mapper.SubjectMapper;
import sk.myproject.faktoorka.repositories.SubjectRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;
    private final SubjectMapper subjectMapper;

    public void createNewSubject(SubjectReq req) {
        subjectRepo.saveAndFlush(subjectMapper.toSubjectEntity(req));
    }

    public List<SubjectRes> getSubjects() {
        List<Subject> invoices = subjectRepo.findAll();
        return invoices
                .parallelStream()
                .map(subjectMapper::toSubjectRes)
                .collect(Collectors.toList());
    }
}
