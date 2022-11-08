package ricotunes.services.card.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"phone"}),
        @UniqueConstraint(columnNames = {"email"})})
public class User extends DateAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "username")
    @Size(max = 15)
    private String username;

    @NotBlank
    @Column(name = "fullname")
    @Size(max = 15)
    private String fullname;

    @NotBlank
    @Column(name = "phone")
    @Size(max = 15)
    private String phone;

    @NotBlank
    @NaturalId
    @Size(max = 40)
    @Column(name = "email")
    @Email
    private String email;


    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100)
    @Column(name = "password")
    private String password;

//
//    @JoinColumn(name = "wallet_id")
//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    private Wallet wallet;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

//    public User(String username, String phone, String email, String password) {
//    }

    public User() {
    }


    public User(String username, String fullname,String phone, String email, String password) {
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.password = password;

    }


    public List<Role> getRoles() {
        return roles == null ? null : new ArrayList<>(roles);
    }

    public void setRoles(List<Role> roles) {
        if (roles == null) {
            this.roles = null;
        } else {
            this.roles = Collections.unmodifiableList(roles);
        }
    }

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

}


