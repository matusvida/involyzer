package sk.myproject.involyzer.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
@ToString
@Data
public class InvoiceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String sum;
}
