package rest.taxopark.model.logical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Status;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.service.CarService;
import rest.taxopark.model.service.TariffService;
import rest.taxopark.model.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AdminLogical {
    @Autowired
    private UserService userService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CarService carService;

    public Model home(Model model){
        List<User> recentlyRegistered = userService.getAllUsers().stream()
                .filter(user -> user.getStatus().name().equals("BANNED"))
                .collect(Collectors.toList());

        List<User> users = userService.getAllUsers().stream()
                .filter(user -> user.getStatus().name().equals("ACTIVE"))
                .collect(Collectors.toList())
                .stream()
                .filter(user -> user.getRole().name().equals("USER"))
                .collect(Collectors.toList());
        model.addAttribute("usersBanned", recentlyRegistered);
        model.addAttribute("users", users);
        model.addAttribute("u", new User());

        return model;
    }

    public Model userManager(Model model, Long id){
        User user = userService.getUserById(id);

        model.addAttribute("user", user);

        return model;
    }

    public void setActiveStatus(Long id){
        User user = userService.getUserById(id);
        user.setStatus(Status.ACTIVE);
        userService.saveUpdateUser(user);
    }

    public void addEarned(Long id, Long earned){
        User user = userService.getUserById(id);
        user.setEarned(user.getEarned() + earned);

        userService.saveUpdateUser(user);
    }

    public Model carsGet(Model model){
        List<Car> cars = carService.getAllCar()
                .stream()
                .filter(car -> car.isBelongs())
                .sorted((o1, o2) -> o1.getId().compareTo(o2.getId()))
                .collect(Collectors.toList());

        model.addAttribute("cars", cars);
        model.addAttribute("c", new Car());

        return model;
    }

    public void carAdd(String model, String regDate, Long mileage, String vin) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date = formatter.parse(regDate);

        Car car = new Car();
        car.setBelongs(true);
        car.setCreateDate(date);
        car.setModel(model);
        car.setMileage(mileage);
        car.setVin(vin);

        carService.save(car);
    }

    public void deleteCar(Long id){
        carService.deleteById(id);
    }

    public Model tariffs(Model model){
        List<Tariff> tariffs = tariffService.getAllTariffs();
        model.addAttribute("tariffs", tariffs);
        model.addAttribute("t", new Tariff());

        return model;
    }

    public void deleteTariff(Long id){
        tariffService.deleteTariffById(id);
    }

    public void addTariff(int comPer, int carRent, int aggRent){
        Tariff tariff = new Tariff(comPer, carRent, aggRent);
        tariffService.save(tariff);
    }

}
