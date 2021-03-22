package sk.myproject.faktoorka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.myproject.faktoorka.entities.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> { }
