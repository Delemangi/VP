package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByFullNameNameContains(String text);

    List<Teacher> findAllByFullNameSurnameContains(String text);
}
