package rest.taxopark.model.entites;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Date year;

    @Column(name = "mileage")
    private int mileage;
}
