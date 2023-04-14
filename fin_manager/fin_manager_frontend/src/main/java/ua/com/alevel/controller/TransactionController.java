package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.api.TransactionApiService;
import ua.com.alevel.model.AccountModel;
import ua.com.alevel.model.TransactionFormModel;
import ua.com.alevel.model.UserAccountsModel;
import ua.com.alevel.model.UserModel;

@Controller
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionApiService transactionApiService;
    private final AccountApiService accountApiService;

    //+
    @GetMapping("/{userId}/{accountId}/new")
    public String transactionForm(@PathVariable Long userId, @PathVariable Long accountId, Model model) {
        AccountModel accountModel = accountApiService.findById(accountId).get();
        model.addAttribute("account", accountModel);
        model.addAttribute("transaction", new TransactionFormModel());
        return "pages/transaction_form";
    }

    //+
    @PostMapping("/{userId}/{accountId}/new")
    public String transactionSubmit(@ModelAttribute("transaction") TransactionFormModel transactionFormModel, @PathVariable Long userId, @PathVariable Long accountId) {
//        @ModelAttribute("account") AccountModel accountFromModel,
        //System.out.println("accountId: " + accountId);


        String accountFromNumber = accountApiService.findById(accountId).get().getAccountNumber();
        transactionFormModel.setFromAccountNumber(accountFromNumber);
        System.out.println(transactionFormModel.toString());
        String status = transactionApiService.createTransaction(transactionFormModel, userId, accountId);
//        System.out.println(accountFromModel);
//        String accountNumber = transactionForm.getToAccount().getAccountNumber();
//        AccountModel accountToModel = accountApiService.findByAccountNumber(accountNumber).get();
//        System.out.println(accountToModel);
        //TODO if all is ok - return success, if false - return fail
        if (!status.equals("")) {
            return "pages/transaction_success";
        }
        return "pages/transaction_failed";
    }
}
