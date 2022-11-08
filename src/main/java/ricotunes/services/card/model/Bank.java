package ricotunes.services.card.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String bankCode;
}
