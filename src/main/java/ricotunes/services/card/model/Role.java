package ricotunes.services.card.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import ricotunes.services.card.enums.RoleName;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name")
    private RoleName roleName;

}