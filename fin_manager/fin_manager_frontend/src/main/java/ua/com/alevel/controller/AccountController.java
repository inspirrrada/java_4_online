package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.model.UserAccountsModel;

import java.util.Optional;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountApiService accountApiService;

    //+
    @GetMapping("/{id}")
    public String findUserAccounts(@PathVariable Long id, Model model) {
        Optional<UserAccountsModel> userAccountsModelOptional = accountApiService.findAllAccountsByUserId(id);
        if (userAccountsModelOptional.isPresent()) {
            model.addAttribute("user", userAccountsModelOptional.get());
            return "pages/user_accounts";
        }
        return "pages/404";
    }
}
