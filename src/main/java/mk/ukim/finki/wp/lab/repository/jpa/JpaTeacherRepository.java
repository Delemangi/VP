package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeacherRepository extends JpaRepository<Teacher, Long> {
}
