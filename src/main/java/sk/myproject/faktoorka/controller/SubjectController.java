package sk.myproject.faktoorka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.myproject.faktoorka.api.SubjectsApi;
import sk.myproject.faktoorka.api.model.SubjectRes;
import sk.myproject.faktoorka.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SubjectController implements SubjectsApi {

    private final SubjectService subjectService;

    @Override
    public ResponseEntity<List<SubjectRes>> getSubjectList() {
        return new ResponseEntity<>(subjectService.getSubjects(), HttpStatus.OK);
    }
}
