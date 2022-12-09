package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaGradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByCourseCourseId(Long courseID);

    Grade findByCourseCourseIdAndStudentUsername(Long courseID, String username);

    List<Grade> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
