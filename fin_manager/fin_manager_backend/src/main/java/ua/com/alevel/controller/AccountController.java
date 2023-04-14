package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.AccountDTO;
import ua.com.alevel.dto.UserAccountsDTO;
import ua.com.alevel.facade.AccountFacade;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountFacade accountFacade;

    @GetMapping
    public ResponseEntity<Collection<AccountDTO>> findAll() {
        return ResponseEntity.ok(accountFacade.findAll());
    }

    //+
    @GetMapping("/{id}")
    public ResponseEntity<UserAccountsDTO> findAllAccountsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(accountFacade.findAllAccountsByUserId(id));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(accountFacade.findById(id));
//    }

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
}
