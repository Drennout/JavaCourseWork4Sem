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

    @Column(name = "belongs")
    private boolean isBelongs;

    @Column (name = "mileage")
    private Long mileage;

    @Column (name = "create_date")
    private Date createDate;

}
