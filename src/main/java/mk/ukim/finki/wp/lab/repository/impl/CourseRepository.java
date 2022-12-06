package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.boostrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }

    public Course findById(Long courseId) {
        return DataHolder.courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        Course course = DataHolder.courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);

        if (course == null) {
            return null;
        }

        return course.getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        course.getStudents().add(student);
        return course;
    }

    public Course addCourse(Course course) {
        DataHolder.courses.add(course);
        return course;
    }

    public boolean deleteCourse(Long id) {
        Course course = DataHolder.courses.stream().filter(c -> c.getCourseId().equals(id)).findFirst().orElse(null);

        if (course == null) {
            return false;
        }

        DataHolder.courses.remove(course);
        return true;
    }

    public List<Course> search(String term) {
        return DataHolder.courses.stream().filter(c -> c.getName().contains(term) || c.getDescription().contains(term)).toList();
    }
}
