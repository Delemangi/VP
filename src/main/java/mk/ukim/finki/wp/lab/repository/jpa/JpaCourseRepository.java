package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContains(String term);
}
