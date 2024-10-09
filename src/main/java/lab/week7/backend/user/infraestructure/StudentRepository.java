package lab.week7.backend.user.infraestructure;

import lab.week7.backend.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
