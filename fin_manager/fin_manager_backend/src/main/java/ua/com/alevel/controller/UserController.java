package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.dto.UserFinanceDTO;
import ua.com.alevel.facade.UserFacade;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<Collection<UserDTO>> findAll() {
        return ResponseEntity.ok(userFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFinanceDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @PostMapping
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
}
