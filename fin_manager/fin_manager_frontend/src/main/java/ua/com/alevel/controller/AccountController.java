package ua.com.alevel.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.model.AccountModel;
import ua.com.alevel.model.AccountStatementModel;
import ua.com.alevel.model.DateFilters;
import ua.com.alevel.model.UserAccountsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountApiService accountApiService;

    //+
    @GetMapping("/{id}/all")
    public String findUserAccounts(@PathVariable Long id, Model model) {
        Optional<UserAccountsModel> userAccountsModelOptional = accountApiService.findAllAccountsByUserId(id);
        if (userAccountsModelOptional.isPresent()) {
            model.addAttribute("user", userAccountsModelOptional.get());
            return "pages/user_accounts";
        }
        return "pages/404";
    }

    @GetMapping("/{userId}/{accountId}/filters")
    public String showAccountFiltersForm(@PathVariable Long userId, @PathVariable Long accountId, Model model) {
        model.addAttribute("filters", new DateFilters());
        return "pages/account_filters_form";
    }

    @PostMapping("/{userId}/{accountId}/filters")
    public String getFiltersValue(@PathVariable Long accountId, Model model, @ModelAttribute("filters") DateFilters dateFilters, @PathVariable String userId) {
//        Calendar calendar = Calendar.getInstance();
//        DateFilters dateFilters = new DateFilters();
//        dateFilters.setToDate(calendar.getTime());
//        model.addAttribute("end", calendar.getTime());
//        calendar.set(Calendar.YEAR, 0);
//        dateFilters.setFromDate(calendar.getTime());
//        model.addAttribute("start", calendar.getTime());
        System.out.println("filters:  @PostMapping(\"/statement/{accountId}/filters\")" + dateFilters.getFromDate());
        return "pages/account_statement";
    }

    @GetMapping("/{userId}/{accountId}/statement")
    public String getAccountStatement(@PathVariable Long userId, @PathVariable Long accountId, Model model, @ModelAttribute DateFilters dateFilters, @RequestParam(value = "fromDate", required = false) String fromDate, @RequestParam(value = "toDate", required = false) String toDate, HttpSession session) {
        System.out.println("filters @GetMapping(\"/statement/{accountId}\"):" + dateFilters.getFromDate());
        Collection<AccountStatementModel> accountStatementModel = accountApiService.getAccountStatement(userId, accountId, fromDate, toDate);
        model.addAttribute("statement", accountStatementModel); //TODO перевірка чи є Optional
        session.setAttribute("statement", accountStatementModel);
        AccountModel accountModel = accountApiService.findById(accountId).get(); ////TODO перевірка чи є Optional
        model.addAttribute("accountModel", accountModel);
        System.out.println("accountStatementModel from statement: " + accountStatementModel);
        return "pages/account_statement";
    }
}
