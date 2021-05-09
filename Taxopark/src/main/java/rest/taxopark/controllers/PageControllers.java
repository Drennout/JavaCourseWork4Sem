package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rest.taxopark.model.service.CarService;
import rest.taxopark.model.service.TariffService;
import rest.taxopark.model.service.UserService;

@Controller
public class PageControllers {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private TariffService tariffService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("user/account")
    public String myAccount(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String useremail = auth.getName();
        model.addAttribute("user", userService.getUserByEmail(useremail));

        model.addAttribute("tariff", tariffService.
                getTariffById(
                        userService.getUserByEmail(useremail).getTariffId())
        );

        model.addAttribute("car", carService.
                getCarById(
                        userService.getUserByEmail(useremail).getCarId())
        );


        return "userAccount";
    }
}
