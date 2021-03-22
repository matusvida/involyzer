package sk.myproject.faktoorka.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"id"})
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ico;
    private String dic;
    private String icDph;
    private Boolean isDphPurchaser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaser_address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaser_personalinfo_id", referencedColumnName = "id")
    private PersonalInfo personalInfo;
}
