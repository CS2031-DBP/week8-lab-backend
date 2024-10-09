package week8.lab.backend.user.infraestructure;

import week8.lab.backend.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
