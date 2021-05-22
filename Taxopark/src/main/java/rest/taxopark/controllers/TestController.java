package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.logical.AccountLogical;
import rest.taxopark.model.repository.CarRepository;
import rest.taxopark.model.repository.UserRepository;
import rest.taxopark.model.service.TariffService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRepository ur;

    @Autowired
    private CarRepository cr;


    @GetMapping
    @ResponseBody
    public List testAll(){
        return ur.findAll();
    }

    @GetMapping("delcar/{id}")
    @ResponseBody
    public List<Car> delCar(@PathVariable Long id){
        cr.deleteById(id);
        return cr.findAll();
    }

    @GetMapping("car")
    @ResponseBody
    public List<Car> cars(){
        return cr.findAll();
    }

    @GetMapping("/users")
    @ResponseBody
    public List test(Model model){
        List<User> users = ur.findAll();

        return users;
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model){
        User user = ur.findById(id).get();
        model.addAttribute("user", user);
        return "userById";
    }

    @GetMapping("test")
    @ResponseBody
    public String getPar(@RequestParam String name){
        return name;
    }

}
