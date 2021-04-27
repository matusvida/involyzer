package sk.myproject.faktoorka.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "invoice_service")
@Data
@NoArgsConstructor
public class InvoiceService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "invoice_id")
	private long invoiceId;

	@Column(name = "service_id")
	private long serviceId;

}
