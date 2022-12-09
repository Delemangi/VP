package mk.ukim.finki.wp.lab.repository.impl;

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

    public List<Teacher> findAllByNameOrSurname(String term) {
        return DataHolder.teachers.stream().filter(t -> t.getFullName().getName().contains(term) || t.getFullName().getSurname().contains(term)).toList();
    }
}
