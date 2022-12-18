package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);

    List<Student> findAllByNameContainsOrSurnameContains(String text, String text1);
}
