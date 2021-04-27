package sk.myproject.faktoorka.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "personalinfo")
@Data
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String phone;
    private String email;
    private String website;
    private String bank;
    private String iban;
    @Column(name = "account_number")
    private String accountNumber;
}
