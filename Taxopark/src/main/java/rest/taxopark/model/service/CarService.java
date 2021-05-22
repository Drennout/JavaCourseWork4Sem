package rest.taxopark.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepo;

    public Car getCarById(Long id){
        return carRepo.findById(id).get();
    }

    public List<Car> getAllCar(){
        List<Car> cars = new ArrayList<>();

        for (Car c: carRepo.findAll())
            cars.add(c);

        return cars;
    }

    public void save(Car car){
        carRepo.save(car);
    }

    public void deleteById(Long id){
        carRepo.deleteById(id);
    }
}
