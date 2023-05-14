package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/personal")
public class PersonalController {

    @GetMapping("/home")
    public String home() {
        return "pages/personal/home";
    }

    @GetMapping("/account")
    public String account() {
        return "pages/personal/account";
    }
}
