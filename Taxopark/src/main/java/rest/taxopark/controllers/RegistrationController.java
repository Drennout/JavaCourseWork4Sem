package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.logical.RegistrationLogical;

import java.text.ParseException;

@Controller
public class RegistrationController {
    private User user;
    private Car car;

    @Autowired
    RegistrationLogical rl;

    @PostMapping("/registration")
    public String addPersonData(@RequestParam String name,
                                        @RequestParam String lastname,
                                        @RequestParam String email,
                                        @RequestParam String bankCard,
                                        @RequestParam String password,
                                        @RequestParam String confPass){
        user = rl.firstStep(name, lastname, email, password, confPass, bankCard);
        return "redirect:/registration/car";
    }

    @GetMapping("registration/car")
    public String addCarGet(Model model){
        model = rl.secondStepGet(model);
        return "registration-car";
    }

    @PostMapping("registration/car")
    public String addCarPost(@RequestParam Long id){
        car = rl.secondStepRentPost(id);
        return "redirect:/registration/tariff";
    }

    @PostMapping("registration/car/owner")
    public String addCarOwner(@RequestParam String model,
                              @RequestParam String regDate,
                              @RequestParam Long mileage,
                              @RequestParam String vin) throws ParseException {
        car = rl.secondStepPostOwner(model, regDate, mileage, vin);
        return "redirect:/registration/tariff";
    };

    @GetMapping("/registration/tariff")
    public String addTariff(Model model){
        model = rl.thirdStep(model);
        return "registration-tariff";
    }

    @PostMapping("/registration/tariff")
    public String addTariffPost(@RequestParam Long id){
        user.setTariffId(id);
        System.out.println(user.toString());
        System.out.println(car.toString());
        user = rl.creatingUser(user, car);
        return "redirect:/user/account";
    }

}
