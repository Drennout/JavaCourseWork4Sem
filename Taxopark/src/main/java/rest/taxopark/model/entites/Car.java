package rest.taxopark.model.entites;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "belongs")
    private boolean isBelongs;

    @Column (name = "mileage")
    private Long mileage;

    @Column (name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}
