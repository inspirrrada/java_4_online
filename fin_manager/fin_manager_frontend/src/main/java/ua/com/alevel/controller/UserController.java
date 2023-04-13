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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
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

    @GetMapping("/statement/{accountId}/filters")
    public String getFilters(@PathVariable Long accountId, Model model) {
//        Calendar calendar = Calendar.getInstance();
//        DateFilters dateFilters = new DateFilters();
//        dateFilters.setToDate(calendar.getTime());
//        model.addAttribute("end", calendar.getTime());
//        calendar.set(Calendar.YEAR, 0);
//        dateFilters.setFromDate(calendar.getTime());
//        model.addAttribute("start", calendar.getTime());
        model.addAttribute("filters", new DateFilters());
        return "pages/dates_filter";
    }

    @PostMapping("/statement/{accountId}/filters")
    public String getFiltersValue(@PathVariable Long accountId, Model model,  @ModelAttribute("filters") DateFilters dateFilters) {
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

    @GetMapping("/statement/{accountId}")
    public String getAccountStatement(@PathVariable Long accountId, Model model, @ModelAttribute DateFilters dateFilters, @RequestParam(value = "fromDate", required = false) String fromDate, @RequestParam(value = "toDate", required = false) String toDate) {
        System.out.println("filters @GetMapping(\"/statement/{accountId}\"):" + dateFilters.getFromDate());
        ZoneOffset offset = OffsetDateTime.now().getOffset();
       String dateTime = dateFilters.getFromDate() + " 00:00:000";
        String dateTime2 = dateFilters.getToDate() + " 23:59:999";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(dateTime);
            date2 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(dateTime2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        OffsetDateTime ofd = date1.toInstant().atOffset(offset);
        ofd = ofd.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(000000000);
        OffsetDateTime ofd2 = date2.toInstant().atOffset(offset);
        ofd2 = ofd2.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999000);
        System.out.println(ofd);
        System.out.println(ofd2);
//        System.out.println("fromDate:" + fromDate);
//        System.out.println("toDate:" + toDate);
//        System.out.println(OffsetDateTime.parse(dateFilters.getFromDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Collection<AccountStatementModel> accountStatementModel = accountApiService.getAccountStatement(accountId, fromDate, toDate);
        for (AccountStatementModel statementModel : accountStatementModel) {
            statementModel.setStartDate(ofd);
            statementModel.setEndDate(ofd2);
        }
        model.addAttribute("statement", accountStatementModel); //TODO перевірка чи є Optional
        return "pages/account_statement";
    }

}
