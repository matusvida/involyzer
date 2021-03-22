package sk.myproject.faktoorka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import sk.myproject.faktoorka.api.SubjectApi;
import sk.myproject.faktoorka.api.SubjectsApi;
import sk.myproject.faktoorka.api.model.SubjectReq;
import sk.myproject.faktoorka.api.model.SubjectRes;
import sk.myproject.faktoorka.service.SubjectService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SubjectController implements SubjectsApi, SubjectApi {

    private final SubjectService subjectService;

    @Override
    @RequestMapping("subject")
    public ResponseEntity<Void> createSubject(@Valid SubjectReq subjectReq) {
        subjectService.createNewSubject(subjectReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<SubjectRes>> getSubjectList() {
        return new ResponseEntity<>(subjectService.getSubjects(), HttpStatus.OK);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }
}
