package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.boostrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {
    public List<Teacher> findAll() {
        return DataHolder.teachers;
    }

    public Teacher findById(Long id) {
        return DataHolder.teachers.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
