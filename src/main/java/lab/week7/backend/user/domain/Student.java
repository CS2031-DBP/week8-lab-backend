package lab.week7.backend.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private Integer age;

    @NonNull
    private String description;

    @NonNull
    private String password;

}
