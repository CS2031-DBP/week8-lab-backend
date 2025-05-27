package week8.lab.backend.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterRequestDto {
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Integer age;

    private String description;

    private String password;
}
