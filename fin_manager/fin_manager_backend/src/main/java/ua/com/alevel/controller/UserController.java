package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.AccountStatementDTO;
import ua.com.alevel.dto.TransactionFormDTO;
import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.dto.UserFinanceDTO;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.Transaction;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserFacade userFacade;
    private final TransactionFacade transactionFacade;

    @GetMapping
    public ResponseEntity<Collection<UserDTO>> findAll() {
        return ResponseEntity.ok(userFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFinanceDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> create(@RequestBody UserDTO dto) {
        userFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        userFacade.update(id, dto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        userFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/{accountId}/transaction")
    public ResponseEntity<Boolean> create(@RequestBody TransactionFormDTO dto, @PathVariable Long accountId) {
        transactionFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping( value = "/statement/{accountId}")
    public ResponseEntity<Collection<AccountStatementDTO>> getStatement(@PathVariable Long accountId, @RequestParam(value = "fromDate", required = false) String fromDate, @RequestParam(value = "toDate", required = false) String toDate) {
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
        return ResponseEntity.ok(transactionFacade.getStatement(fromDateT, toDateT, accountId));
    }
}
