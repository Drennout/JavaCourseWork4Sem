package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rest.taxopark.model.dto.UserMO;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.service.CarService;
import rest.taxopark.model.service.TariffService;
import rest.taxopark.model.service.UserService;

import java.util.ArrayList;


@Controller
@RequestMapping("/user/account/edit")
public class AccountController {
    @Autowired
    private UserMO userMO;
    @Autowired
    private UserService userService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CarService carService;

    @GetMapping("/person")
    public String getRedactorPage(Model model){
        userMO.setUserFromSession();
        model.addAttribute("user", userMO.getUser());
        return "redactor-person";
    }

    @PostMapping("/person")
    public String postRedactPage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String lastname){

        userMO.setUserFromSession();
        User user = userMO.getUser();
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(lastname);

        userMO.updateUserPersonInfo(user);

        return "redirect:/user/account";
    }

    @GetMapping("/tariff")
    public String getEditTariff(Model model){
        model.addAttribute("currentTariff", userMO.getTariff());
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        model.addAttribute("t", new Tariff());
        return "/redactor-tariff";
    }

    @PostMapping("/tariff")
    public String postEditTariff(@RequestParam Long tar){
        userMO.setUserFromSession();
        User user = userMO.getUser();
        user.setTariffId(tar);

        userMO.updateUserPersonInfo(user);
        return "redirect:/user/account";
    }

    @GetMapping("/car")
    public String getEditCar(Model model){
        model.addAttribute("currentCar", userMO.getCar());
        model.addAttribute("cars", carService.getAllCar());
        model.addAttribute("c", new Car());

        return "redactor-car";
    }

    @PostMapping("/car")
    public String postEditCar(@RequestParam Long carId){
        userMO.setUserFromSession();
        User user = userMO.getUser();
        user.setCarId(carId);

        userMO.updateUserPersonInfo(user);
        return "redirect:/user/account";
    }
}
