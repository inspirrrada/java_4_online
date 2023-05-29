package ua.com.alevel.controller.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

    @GetMapping("/about")
    public String showAboutInfo() {
        return "pages/open/about_us";
    }

    @GetMapping("/terms")
    public String showOrderConditions() {
        return "pages/open/order_conditions";
    }

    @GetMapping("/contact")
    public String showContactForm() {
        return "pages/open/contact";
    }
}
