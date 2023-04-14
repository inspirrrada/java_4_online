package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.TransactionFormDTO;
import ua.com.alevel.facade.TransactionFacade;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionFacade transactionFacade;

    //+
    @PostMapping("/{accountId}/new")
    public ResponseEntity<Boolean> create(@RequestBody TransactionFormDTO dto, @PathVariable Long accountId) {
        transactionFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}
