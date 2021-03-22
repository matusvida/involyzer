package sk.myproject.faktoorka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.myproject.faktoorka.entities.Invoice;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> { }
