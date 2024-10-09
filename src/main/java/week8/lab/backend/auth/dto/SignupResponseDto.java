package week8.lab.backend.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupResponseDto {
    private String username;

    private String firstName;

    private String lastName;

    private String password;
}