package week8.lab.backend.account.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import week8.lab.backend.account.domain.Account;
import week8.lab.backend.account.domain.AccountService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllUsers() {
        return ResponseEntity.ok(accountService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Account> createUser(@RequestBody Account account) {
        Account createdAccount = accountService.createUser(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAccount.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateUser(@PathVariable Long id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateUser(id, account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        accountService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
