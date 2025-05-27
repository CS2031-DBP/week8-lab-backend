package week8.lab.backend.auth.domain;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import week8.lab.backend.account.domain.Account;
import week8.lab.backend.account.infrastructure.AccountRepository;
import week8.lab.backend.auth.dto.AuthResponseDto;
import week8.lab.backend.auth.dto.LoginRequestDto;
import week8.lab.backend.auth.dto.RegisterRequestDto;
import week8.lab.backend.config.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        Account account = accountRepository.findByEmail(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwtService.generateToken(account));
        return authResponseDto;
    }

    public AuthResponseDto signup(RegisterRequestDto registerRequestDto) {
        if (accountRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exits");
        }

        Account account = modelMapper.map(registerRequestDto, Account.class);
        account.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        accountRepository.save(account);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwtService.generateToken(account));
        return authResponseDto;
    }
}
