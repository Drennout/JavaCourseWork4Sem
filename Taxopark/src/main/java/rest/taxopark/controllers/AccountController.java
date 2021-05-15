package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rest.taxopark.model.logical.AccountLogical;



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
            @RequestParam String lastname){

        acc.userAccountEditPersonPost(name, lastname, email);

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
}
