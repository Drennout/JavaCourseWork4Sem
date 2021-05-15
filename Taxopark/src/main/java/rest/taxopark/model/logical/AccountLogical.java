package rest.taxopark.model.logical;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.service.CarService;
import rest.taxopark.model.service.TariffService;
import rest.taxopark.model.service.UserService;

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

    public Model userAccount(Model model){
        model.addAttribute("user", getUser());
        model.addAttribute("tariff", getTariff());
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
}
