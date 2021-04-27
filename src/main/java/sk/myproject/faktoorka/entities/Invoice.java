package sk.myproject.faktoorka.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date month;
    @Column(name = "total_excl_vat")
    private BigDecimal totalExclVat;
    @Column(name = "vat_total")
    private BigDecimal vatTotal;
    private BigDecimal total;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date")
    private Date issueDate;
    @Temporal(TemporalType.DATE)
    @Basic
    private Date dueDate;
    @Column(name = "pdf_invoice")
    @Basic(fetch = FetchType.LAZY)
    private byte[] pdfInvoice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Subject sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaser_id", referencedColumnName = "id")
    private Subject purchaser;

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name = "invoice_service",
        joinColumns = {@JoinColumn(name = "invoice_id")},
        inverseJoinColumns = {@JoinColumn(name = "id")} )
    private Set<Service> services;
}
