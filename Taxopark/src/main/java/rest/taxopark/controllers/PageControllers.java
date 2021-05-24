package rest.taxopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rest.taxopark.model.logical.AccountLogical;

@Controller
public class PageControllers {
    @Autowired
    private AccountLogical acc;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("user/account")
    public String myAccount(Model model) {
        model = acc.userAccount(model);
        return "userAccount";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }
}
