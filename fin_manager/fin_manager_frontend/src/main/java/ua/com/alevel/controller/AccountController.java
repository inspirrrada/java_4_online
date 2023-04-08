package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.api.AccountApiService;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountApiService accountApiService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("accounts", accountApiService.findAll());
        return "user_accounts";
    }
}
