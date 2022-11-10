package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    List<Course> getCourses();

    Course getCourseById(Long courseId);
    Course addCourse(String name, String description, Long teacherId);
    boolean deleteCourse(Long id);

    void editCourse(Long id, String name, String description, Long teacher);
}
