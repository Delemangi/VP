package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.repository.jpa.JpaGradeRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    private final JpaGradeRepository gradeRepository;

    public GradeServiceImpl(JpaGradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> findByCourseId(Long courseID) {
        return gradeRepository.findAllByCourseCourseId(courseID);
    }

    @Override
    public Grade findByCourseIdAndStudentUsername(Long courseID, String username) {
        return gradeRepository.findByCourseCourseIdAndStudentUsername(courseID, username);
    }

    @Override
    public void save(Grade g) {
        gradeRepository.save(g);
    }

    @Override
    public void deleteAll(Iterable<Grade> grades) {
        gradeRepository.deleteAll(grades);
    }
}
