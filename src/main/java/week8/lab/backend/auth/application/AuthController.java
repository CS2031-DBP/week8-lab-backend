package week8.lab.backend.auth.application;

import week8.lab.backend.auth.domain.AuthService;
import week8.lab.backend.auth.dto.AuthResponseDto;
import week8.lab.backend.auth.dto.LoginRequestDto;
import week8.lab.backend.auth.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody SignupResponseDto signupResponseDto) {
        return ResponseEntity.ok(authService.signup(signupResponseDto));
    }
}