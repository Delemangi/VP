package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findByCourseId(Long courseID);

    Grade findByCourseIdAndStudentUsername(Long courseID, String username);

    void save(Grade g);

    void deleteAll(Iterable<Grade> grades);
}
