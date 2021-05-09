package rest.taxopark.model.entites;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String pass;
}
