package rest.taxopark.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.repository.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepo;

    public Car getCarById(Long id){
        return carRepo.findById(id).get();
    }
}
