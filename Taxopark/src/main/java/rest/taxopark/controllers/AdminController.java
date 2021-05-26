package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.logical.AdminLogical;

import java.text.ParseException;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('admin')")
public class AdminController {
    @Autowired
    private AdminLogical al;

    @GetMapping("/")
    public String home(Model model){
        model = al.home(model);
        return "admin-home";
    }

    @GetMapping("/user/{id}/registered")
    public String userManagerRegistered(@PathVariable Long id, Model model){
        model = al.userManager(model, id);
        return "user-managet-registered";
    }

    @PostMapping("/user/{id}/status")
    public String changeStatus(@PathVariable Long id){
        al.setActiveStatus(id);
        return "redirect:/admin/";
    }

    @GetMapping("/user/{id}")
    public String userManager(@PathVariable Long id, Model model){
        model = al.userManager(model, id);
        return "user-manager";
    }

    @PostMapping("/user/{id}/earned")
    public String addEarned(@PathVariable Long id, @RequestParam Long earned){
        al.addEarned(id, earned);
        return "redirect:/admin/user/" + id;
    }

    @GetMapping("/cars")
    public String cars(Model model){
        model = al.carsGet(model);

        return "cars";
    }

    @PostMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id){
        al.deleteCar(id);
        return "redirect:/admin/cars";
    }

    @GetMapping("/cars/add")
    public String addCar(){
        return "admin-car-add";
    }

    @PostMapping("cars/add")
    public String addCarPost(@RequestParam String model,
                             @RequestParam String regDate,
                             @RequestParam Long mileage,
                             @RequestParam String vin) throws ParseException {
        al.carAdd(model, regDate, mileage, vin);
        return "redirect:/admin/cars";
    }


    @GetMapping("/tariffs")
    public String tariffs(Model model){
        model = al.tariffs(model);
        return "tariffs";
    }

    @PostMapping("/tariffs/delete/{id}")
    public String deleteTariff(@PathVariable Long id){
        al.deleteTariff(id);
        return "redirect:/admin/tariffs";
    }

    @GetMapping("/tariffs/add")
    public String addTariff(){
        return "add-tariff";
    }

    @PostMapping("/tariffs/add")
    public String addTariffPost(@RequestParam int comPer,
                                @RequestParam int carRent,
                                @RequestParam int aggRent){
        al.addTariff(comPer, carRent, aggRent);
        return "redirect:/admin/tariffs";
    }
}
