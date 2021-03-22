package sk.myproject.faktoorka.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date month;
    private String service;
    private Integer quantity;
    private String unit;
    @Column(name = "price_per_unit")
    private Integer pricePerUnit;
    @Column(name = "total_excl_vat")
    private BigDecimal totalExclVat;
    private Integer vat;
    private BigDecimal total;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Temporal(TemporalType.DATE)
    @Basic
    private Date dueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Subject sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaser_id", referencedColumnName = "id")
    private Subject purchaser;
}
