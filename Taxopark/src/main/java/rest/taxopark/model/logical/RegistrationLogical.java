package rest.taxopark.model.logical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.service.CarService;
import rest.taxopark.model.service.TariffService;
import rest.taxopark.model.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class RegistrationLogical {
    @Autowired
    private UserService userService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CarService carService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User firstStep(String name, String lastname, String email, String password, String confPass){
        if(password.equals(confPass)){
            User user = new User();
            user.setPass(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setFirstName(name);
            user.setLastName(lastname);
            user.setEarned(0l);
            return user;
        }
        else
            return null;
    }

    public Model secondStepGet(Model model){
        List<Car> cars = new ArrayList<>();
        List<Long> carId = new ArrayList<>();

        for (User u: userService.getAllUsers())
            carId.add(u.getCar().getId());

        for (Car car: carService.getAllCar()){
            if (car.isBelongs()){
                if (!carId.contains(car.getId()))
                    cars.add(car);
            }
        }
        model.addAttribute("cars", cars);
        model.addAttribute("c", new Car());
        return model;
    }

    public Car secondStepPostOwner(String model, String regDate, Long mileage) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date = formatter.parse(regDate);

        Car car = new Car();
        car.setBelongs(false);
        car.setCreateDate(date);
        car.setModel(model);
        car.setMileage(mileage);
        return car;
    }

    public Car secondStepRentPost(Long id){
        return carService.getCarById(id);
    }

    public Model thirdStep(Model model){
        model.addAttribute("t", new Tariff());
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return model;
    }

    public User creatingUser(User user, Car car){
        if(carService.getAllCar().contains(car)){
            System.out.println("contains");
            userService.saveUpdateUser(user);
            user.setCar(car);
            userService.saveUpdateUser(user);
            return user;
        }
        else{
            System.out.println("not contains");
            carService.save(car);
            user.setCar(car);
            userService.saveUpdateUser(user);
            return user;
        }
    }

}
