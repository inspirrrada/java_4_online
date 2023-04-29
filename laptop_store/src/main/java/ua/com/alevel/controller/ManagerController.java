package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/manager")
public class ManagerController {

    @GetMapping("/home")
    public String home() {
        return "pages/manager/home";
    }
}
