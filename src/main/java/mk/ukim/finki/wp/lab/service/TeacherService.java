package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();

    Teacher getById(Long id);

    List<Teacher> search(String term);

    Teacher save(String name, String surname);
}
