package week8.lab.backend.account.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import week8.lab.backend.account.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
