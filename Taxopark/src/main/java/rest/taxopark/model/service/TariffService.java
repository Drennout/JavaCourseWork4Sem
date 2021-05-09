package rest.taxopark.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.repository.CarRepository;
import rest.taxopark.model.repository.TariffRepository;

@Service
public class TariffService {
    @Autowired
    private TariffRepository tariffRepository;

    public Tariff getTariffById(Long id){
        return tariffRepository.findById(id).get();
    }
}
