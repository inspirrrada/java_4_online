package ua.com.alevel.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.model.AccountModel;
import ua.com.alevel.model.AccountStatementModel;
import ua.com.alevel.model.DateFilters;
import ua.com.alevel.model.UserAccountsModel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

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

    @GetMapping("/download")
    public void getFile(Model model, HttpSession session, HttpServletResponse response) {
        System.out.println("accountStatementModel from download: " + model.getAttribute("statement"));
        Collection<AccountStatementModel> accountStatementModel = new ArrayList<>();
        if (session.getAttribute("statement") != null) {
            accountStatementModel = (Collection<AccountStatementModel>) session.getAttribute("statement");
            System.out.println(accountStatementModel.toString());
        }

        // set file name and content type
        String filename = "Account_statement.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        // create a csv writer
        StatefulBeanToCsv<AccountStatementModel> writer =
                null;
        try {
            writer = new StatefulBeanToCsvBuilder<AccountStatementModel>
                    (response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // write all employees to csv file
        try {
            writer.write((List<AccountStatementModel>) accountStatementModel);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }

        //return "pages/transaction_success";
    }

}
