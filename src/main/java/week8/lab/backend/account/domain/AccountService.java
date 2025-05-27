package week8.lab.backend.account.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import week8.lab.backend.account.exception.NotFoundException;
import week8.lab.backend.account.infrastructure.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    public Account getUser(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public Account createUser(Account account) {
        return accountRepository.save(account);
    }

    public Account updateUser(Long id, Account account) {
        Account accountToUpdate = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        accountToUpdate.setFirstname(account.getFirstname());
        accountToUpdate.setLastname(account.getLastname());
        accountToUpdate.setEmail(account.getEmail());
        accountToUpdate.setPhone(account.getPhone());
        accountToUpdate.setAge(account.getAge());
        accountToUpdate.setDescription(account.getDescription());
        accountToUpdate.setPassword(account.getPassword());
        return accountRepository.save(accountToUpdate);
    }

    public void deleteUser(Long id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        accountRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return accountRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
