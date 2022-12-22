package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.jpa.JpaTeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final JpaTeacherRepository teacherRepository;

    public TeacherServiceImpl(JpaTeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public List<Teacher> search(String term) {
        Set<Teacher> a = new HashSet<>();

        a.addAll(teacherRepository.findAllByFullNameNameContains(term));
        a.addAll(teacherRepository.findAllByFullNameSurnameContains(term));

        return new ArrayList<>(a);
    }

    @Override
    public Teacher save(String name, String surname) {
        return teacherRepository.save(new Teacher(name, surname));
    }
}
