package rest.taxopark.model.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rest.taxopark.model.entites.Car;
import rest.taxopark.model.entites.Tariff;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.service.CarService;
import rest.taxopark.model.service.TariffService;
import rest.taxopark.model.service.UserService;

@Component
@Data
public class UserMO {
    @Autowired
    private UserService userService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CarService carService;

    private User user;
    private Tariff tariff;
    private Car car;

    public void setUserFromSession(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        this.user = userService.getUserByEmail(userEmail);
        this.tariff = tariffService.getTariffById(user.getTariffId());
        this.car = carService.getCarById(user.getCarId());
    }

    public boolean updateUserPersonInfo(User updateUser){
        return userService.saveUpdateUser(updateUser);
    }
}
