package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.service.TariffService;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TariffService tariffService;

//    @GetMapping("tariff")
//    public String getAllTariff(Model model){
//        model.addAttribute("tariffs", tariffService.getAllTariffs());
//        model.addAttribute("t", new Tariff());
//        return "redactor-tariff";
//    }

    
}
