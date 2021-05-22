package rest.taxopark.model.logical;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
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
@Data
public class AccountLogical {
    @Autowired
    private UserService userService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CarService carService;

    private Authentication auth;

    private User getUser(){
        setAuth(SecurityContextHolder.getContext().getAuthentication());

        return userService.getUserByEmail(auth.getName());
    }

    private Tariff getTariff(){
        return tariffService.getTariffById(getUser().getTariffId());
    }

    private Car getCar(){
        return getUser().getCar();
    }


    public Model userAccount(Model model){
        model.addAttribute("user", getUser());
        model.addAttribute("tariff", getTariff());
        model.addAttribute("car", getCar());
        return model;
    }

    public Model userAccountEditPersonGet(Model model){
        model.addAttribute("user", getUser());

        return model;
    }

    public void userAccountEditPersonPost(String name, String lastname, String email){
        User user = getUser();
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(lastname);

        userService.saveUpdateUser(user);
    }

    public Model userAccountEditTariffGet(Model model){
        model.addAttribute("t", new Tariff());
        model.addAttribute("currentTariff", getTariff());
        model.addAttribute("tariffs", tariffService.getAllTariffs());

        return model;
    }

    public void userAccountEditTariffPost(Long id){
        User user = getUser();
        user.setTariffId(id);

        userService.saveUpdateUser(user);
    }

    public Model userAccountEditCarGet(Model model){
        //access car to choosing
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
        model.addAttribute("curCar", getUser().getCar());
        model.addAttribute("cars", cars);
        model.addAttribute("c", new Car());
        return model;
    }

    public void userAccountEditCarPost(Long id){
        User user = getUser();
        if(!user.getCar().isBelongs()){
            Long delId = user.getCar().getId();
            user.setCar(carService.getCarById(id));
            userService.saveUpdateUser(user);

            carService.deleteById(delId);
            return;
        }

        user.setCar(carService.getCarById(id));
        userService.saveUpdateUser(user);
    }


    public Model userAccountCarAddGet(Model model){
        model.addAttribute("car", getCar());
        if(getCar().isBelongs())
            model.addAttribute("belongsMessage", "No");
        else
            model.addAttribute("belongsMessage", "Yes");
        return model;
    }

    public void userAccountCarAddPost(String model, String regDate, Long mileage) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date = formatter.parse(regDate);

        Car car = new Car();
        car.setBelongs(false);
        car.setCreateDate(date);
        car.setModel(model);
        car.setMileage(mileage);

        carService.save(car);

        User user = getUser();
        user.setCar(car);
        userService.saveUpdateUser(user);
    }
    // delete test
    public List testCar(){
        return userService.getAllUsers();
    }

    // delete test
    public Car testUserCar(){
        return getUser().getCar();
    }

}
