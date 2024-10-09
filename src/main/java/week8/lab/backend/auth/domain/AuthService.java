package week8.lab.backend.auth.domain;

import week8.lab.backend.auth.dto.AuthResponseDto;
import week8.lab.backend.auth.dto.LoginRequestDto;
import week8.lab.backend.auth.dto.SignupResponseDto;
import week8.lab.backend.config.JwtService;
import week8.lab.backend.user.domain.User;
import week8.lab.backend.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwtService.generateToken(user));
        return authResponseDto;
    }

    public AuthResponseDto signup(SignupResponseDto signupResponseDto) {
        if (userRepository.findByUsername(signupResponseDto.getUsername()).isPresent()) {
            throw new RuntimeException("Email already exits");
        }

        User user = modelMapper.map(signupResponseDto, User.class);
        user.setPassword(passwordEncoder.encode(signupResponseDto.getPassword()));
        userRepository.save(user);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwtService.generateToken(user));
        return authResponseDto;
    }
}