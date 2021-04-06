package sk.myproject.faktoorka.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "service_name")
	private String serviceName;
	private BigDecimal quantity;
	private String unit;
	@Column(name = "price_per_unit")
	private BigDecimal pricePerUnit;
	@Column(name = "total_excl_vat")
	private BigDecimal totalExclVat;
	private Integer vat;
	private BigDecimal totalWithVat;

	@ManyToMany(mappedBy = "services")
	private Set<Invoice> invoices = new HashSet<>();


}
