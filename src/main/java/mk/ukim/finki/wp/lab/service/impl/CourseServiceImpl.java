package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enums.Type;
import mk.ukim.finki.wp.lab.repository.jpa.JpaCourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final JpaCourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(JpaCourseRepository courseRepository, StudentService studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course == null) {
            return Collections.emptyList();
        }

        return course.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = studentService.listAll()
                .stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (student == null) {
            return null;
        }

        Course course = courseRepository.findById(courseId).orElse(null);

        if (course == null) {
            return null;
        }

        course.getStudents().add(student);
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public Course addCourse(String name, String description, Long teacherId, String type) {
        Course course = new Course(name, description, teacherService.getById(teacherId), Type.valueOf(type));

        if (getCourses()
                .stream()
                .filter(c -> c.getName().equals(course.getName()))
                .findFirst()
                .orElse(null) != null) {
            return null;
        }

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course editCourse(Long id, String name, String description, Long teacher, String type) {
        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherService.getById(teacher));
        course.setType(Type.valueOf(type));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> search(String term) {
        return courseRepository.findByNameContains(term);
    }

    @Override
    public Course findByCourseId(Long course) {
        return courseRepository.findById(course).orElse(null);
    }

    @Override
    public void save(String name, String description, Long teacher, Long course, String type) {
        Course c = courseRepository.findById(course).orElse(null);

        if (c == null) {
            c = new Course();
        }

        c.setName(name);
        c.setDescription(description);
        c.setTeacher(teacherService.getById(teacher));
        c.setType(Type.valueOf(type));
        courseRepository.save(c);
    }
}
