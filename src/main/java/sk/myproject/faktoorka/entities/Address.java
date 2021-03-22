package sk.myproject.faktoorka.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;
    private String country;
    private String city;
    private String postalNumber;
    private String houseNumber;
}
