package week8.lab.backend.course.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import week8.lab.backend.course.domain.Course;

public interface courseRepository extends JpaRepository<Course, Long> {
}
