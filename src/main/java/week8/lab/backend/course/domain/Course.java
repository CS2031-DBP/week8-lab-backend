package week8.lab.backend.course.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import week8.lab.backend.account.domain.Account;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    @ColumnDefault("false")
    private Boolean isCompleted = false;

    @ManyToOne
    private Account account;
}
