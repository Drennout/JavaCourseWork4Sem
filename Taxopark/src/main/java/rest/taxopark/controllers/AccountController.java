package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rest.taxopark.model.logical.AccountLogical;
import java.text.ParseException;

@Controller
@RequestMapping("/user/account/edit")
public class AccountController {
    @Autowired
    private AccountLogical acc;

    @GetMapping("/person")
    public String getRedactorPage(Model model){
        model = acc.userAccountEditPersonGet(model);
        return "edit-person";
    }

    @PostMapping("/person")
    public String postRedactPage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String lastname,
            @RequestParam String bankCard){

        acc.userAccountEditPersonPost(name, lastname, email, bankCard);

        return "redirect:/user/account";
    }

    @GetMapping("/tariff")
    public String getEditTariff(Model model){
        model = acc.userAccountEditTariffGet(model);
        return "/edit-tariff";
    }

    @PostMapping("/tariff")
    public String postEditTariff(@RequestParam Long id){
        acc.userAccountEditTariffPost(id);
        return "redirect:/user/account";
    }

    @GetMapping("/car")
    public String getEditCar(Model model){
        model = acc.userAccountEditCarGet(model);
        return "edit-car";
    }

    @PostMapping("car")
    public String postEditCar(@RequestParam Long id){
        acc.userAccountEditCarPost(id);
        return "redirect:/user/account";
    }

    @GetMapping("car/add")
    public String getCarAdd(Model model){
        model = acc.userAccountCarAddGet(model);
        return "add-car";
    }

    @PostMapping("car/add")
        public String postCarAdd(@RequestParam String model,
                                 @RequestParam String regDate,
                                 @RequestParam Long mileage,
                                 @RequestParam String vin) throws ParseException {

        acc.userAccountCarAddPost(model, regDate, mileage, vin);
        return "redirect:/user/account";
    }

    @PostMapping("/person/pass")
    public String postPersonPass(@RequestParam String oldPass,
                                 @RequestParam String newPass,
                                 @RequestParam String repeatPass){
        acc.userAccountEditPass(oldPass,newPass,repeatPass);
        return "redirect:/user/account";
    }

    @PostMapping("/write")
    public String writeOffMoney(){
        acc.writeOffMoney();
        return "redirect:/user/account";
    }
}
