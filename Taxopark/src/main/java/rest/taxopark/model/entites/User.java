package rest.taxopark.model.entites;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Data
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String pass;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "firts_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "tariff_id")
    private Long tariffId;

    @Column(name="earned")
    private Long earned;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car", referencedColumnName = "id")
    private Car car;

}
