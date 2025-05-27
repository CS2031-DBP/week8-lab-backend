package week8.lab.backend.auth.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import week8.lab.backend.account.domain.Account;
import week8.lab.backend.account.infrastructure.AccountRepository;

@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final AccountRepository accountRepository;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

    public Account getCurrentUser() {
        String username = getCurrentUsername();
        return accountRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
