package sk.myproject.involyzer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.myproject.involyzer.model.InvoiceDto;

@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceDto, Long> {}
