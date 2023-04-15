package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.AccountDTO;
import ua.com.alevel.dto.AccountStatementDTO;
import ua.com.alevel.dto.UserAccountsDTO;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.TransactionFacade;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountFacade accountFacade;
    private final TransactionFacade transactionFacade;

    @GetMapping
    public ResponseEntity<Collection<AccountDTO>> findAll() {
        return ResponseEntity.ok(accountFacade.findAll());
    }

    //+
    @GetMapping("/{id}/all")
    public ResponseEntity<UserAccountsDTO> findAllAccountsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(accountFacade.findAllAccountsByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountFacade.findById(id));
    }

//    @GetMapping("/{accountNumber}")
//    public ResponseEntity<AccountDTO> findByAccountNumber(@PathVariable String accountNumber) {
//        return ResponseEntity.ok(accountFacade.findByAccountNumber(accountNumber));
//    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody AccountDTO dto) {
        accountFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody AccountDTO dto, @PathVariable Long id) {
        accountFacade.update(id, dto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        accountFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping( value = "/{userId}/{accountId}/statement")
    public ResponseEntity<Collection<AccountStatementDTO>> getAccountStatement(@PathVariable Long accountId, @RequestParam(value = "fromDate", required = false) String fromDate, @RequestParam(value = "toDate", required = false) String toDate, @PathVariable String userId) {
        System.out.println("fromDate backend:" + fromDate);
        System.out.println("toDate backend:" + toDate);
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        String dateTime = fromDate + " 00:00:00.000000000";
        String dateTime2 = toDate + " 23:59:59.999999999";
        Timestamp fromDateT = Timestamp.valueOf(dateTime);
        Timestamp toDateT = Timestamp.valueOf(dateTime2);
        System.out.println("fromDateT: " + fromDateT);
        System.out.println("toDateT: " + toDateT);
//        Date date1 = null;
//        Date date2 = null;
//        try {
//            date1 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(dateTime);
//            date2 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(dateTime2);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        OffsetDateTime ofd = date1.toInstant().atOffset(offset);
//        ofd = ofd.withHour(0)
//                .withMinute(0)
//                .withSecond(0)
//                .withNano(000000000);
//        OffsetDateTime ofd2 = date2.toInstant().atOffset(offset);
//        ofd2 = ofd2.withHour(23)
//                .withMinute(59)
//                .withSecond(59)
//                .withNano(999999000);
//        System.out.println(ofd);
//        System.out.println(ofd2);
        return ResponseEntity.ok(transactionFacade.getAccountStatement(fromDateT, toDateT, accountId));
    }
}
