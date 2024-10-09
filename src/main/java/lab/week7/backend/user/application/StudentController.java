package lab.week7.backend.user.application;

import lab.week7.backend.user.domain.Student;
import lab.week7.backend.user.domain.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllUsers() {
        return ResponseEntity.ok(studentService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Student> createUser(@RequestBody Student student) {
        Student createdStudent = studentService.createUser(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdStudent.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateUser(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateUser(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        studentService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
