package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rest.taxopark.model.dto.UserMO;
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

    @Autowired
    private UserMO userDTO;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("user/account")
    public String myAccount(Model model) {
        userDTO.setUserFromSession();
        model.addAttribute("user", userDTO.getUser());
        model.addAttribute("tariff", userDTO.getTariff());
        model.addAttribute("car", userDTO.getCar());

        return "userAccount";
    }


    //Delete these
    @GetMapping("/test")
    public String getTestUsersDto(){
        userDTO.setUserFromSession();
        System.out.println(userDTO.getUser().getFirstName());
        System.out.println(userDTO.getTariff().getId());
        System.out.println(userDTO.getCar().getModel());
        return "index";
    }
}
