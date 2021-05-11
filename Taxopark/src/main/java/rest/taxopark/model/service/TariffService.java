package rest.taxopark.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.repository.CarRepository;
import rest.taxopark.model.repository.TariffRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TariffService {
    @Autowired
    private TariffRepository tariffRepository;

    public Tariff getTariffById(Long id){
        return tariffRepository.findById(id).get();
    }
    public List<Tariff> getAllTariffs(){
        List<Tariff> tariffs = new ArrayList<>();
        for (Tariff t: tariffRepository.findAll()){
            tariffs.add(t);
        }

        return tariffs;
    }
}
