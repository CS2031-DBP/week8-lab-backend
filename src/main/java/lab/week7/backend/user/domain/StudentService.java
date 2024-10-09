package lab.week7.backend.user.domain;

import lab.week7.backend.user.exception.NotFoundException;
import lab.week7.backend.user.infraestructure.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    public Student getUser(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public Student createUser(Student student) {
        return studentRepository.save(student);
    }

    public Student updateUser(Long id, Student student) {
        Student userToUpdate = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        userToUpdate.setFirstname(student.getFirstname());
        userToUpdate.setLastname(student.getLastname());
        userToUpdate.setEmail(student.getEmail());
        userToUpdate.setPhone(student.getPhone());
        userToUpdate.setAge(student.getAge());
        userToUpdate.setDescription(student.getDescription());
        userToUpdate.setPassword(student.getPassword());
        return studentRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        studentRepository.deleteById(id);
    }
}
