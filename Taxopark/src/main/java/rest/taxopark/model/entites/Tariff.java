package rest.taxopark.model.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.management.ConstructorParameters;
import javax.persistence.*;

@Entity
@Table(name = "tariff")
@Data
@RequiredArgsConstructor
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "commission_percent")
    private int comPer;

    @Column(name = "car_rent")
    private int carRent;

    @Column(name = "aggregate_rent")
    private int aggRent;

    public Tariff(int comPer, int carRent, int aggRent) {
        this.comPer = comPer;
        this.carRent =carRent;
        this.aggRent = aggRent;
    }
}
