package ua.com.alevel.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.api.TransactionApiService;
import ua.com.alevel.api.UserApiService;
import ua.com.alevel.model.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/users"})
@AllArgsConstructor
public class UserController {

    private final UserApiService userApiService;
    private final AccountApiService accountApiService;
    private final TransactionApiService transactionApiService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userApiService.findAll());
        return "pages/users";
    }

    @GetMapping("/{id}")
    public String findUserAccounts(@PathVariable Long id, Model model) {
        Optional<UserFinanceModel> userFinanceModelOptional = userApiService.findById(id);
        if (userFinanceModelOptional.isPresent()) {
            model.addAttribute("user", userFinanceModelOptional.get());
            return "pages/user_accounts";
        }
        return "pages/404";
    }

    @GetMapping("/{id}/{accountId}/transaction")
    public String transactionForm(@PathVariable Long id, @PathVariable Long accountId, Model model) {
        UserFinanceModel userFinanceModel = userApiService.findById(id).get();
        AccountModel accountModel = accountApiService.findById(accountId).get();
        model.addAttribute("user", userFinanceModel);
        model.addAttribute("account", accountModel);
        model.addAttribute("transaction", new TransactionForm()); //new TransactionModel());
        return "pages/transaction_form";

    }

//    @PostMapping("/{id}/{accountId}/transaction")
//    public String transactionSubmit(@ModelAttribute("transaction") TransactionModel transactionModel, @PathVariable Long accountId) {
////        @ModelAttribute("account") AccountModel accountFromModel,
//        //System.out.println("accountId: " + accountId);
//        System.out.println(transactionModel.toString());
//
//        AccountModel accountFromModel = accountApiService.findById(accountId).get();
//        System.out.println(accountFromModel);
//        String accountNumber = transactionModel.getToAccount().getAccountNumber();
//        AccountModel accountToModel = accountApiService.findByAccountNumber(accountNumber).get();
//        System.out.println(accountToModel);
//        //TODO if all is ok - return success, if false - return fail
//        return "pages/transaction_success";
//    }

    @PostMapping("/{accountId}/transaction")
    public String transactionSubmit(@ModelAttribute("transaction") TransactionForm transactionForm, @PathVariable Long accountId) {
//        @ModelAttribute("account") AccountModel accountFromModel,
        //System.out.println("accountId: " + accountId);


        String accountFromNumber = accountApiService.findById(accountId).get().getAccountNumber();
        transactionForm.setFromAccountNumber(accountFromNumber);
        System.out.println(transactionForm.toString());
        transactionApiService.create2(transactionForm, accountId);
//        System.out.println(accountFromModel);
//        String accountNumber = transactionForm.getToAccount().getAccountNumber();
//        AccountModel accountToModel = accountApiService.findByAccountNumber(accountNumber).get();
//        System.out.println(accountToModel);
        //TODO if all is ok - return success, if false - return fail
        return "pages/transaction_success";
    }

    @GetMapping("/statement/{accountId}")
    public String getAccountStatement(@PathVariable Long accountId, Model model) {
        Collection<AccountStatementModel> accountStatementModel = accountApiService.getAccountStatement(accountId);
        model.addAttribute("statement", accountStatementModel); //TODO перевірка чи є Optional
        return "pages/account_statement";
    }

}
