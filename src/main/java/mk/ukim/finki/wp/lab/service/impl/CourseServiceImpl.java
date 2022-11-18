package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId).getStudents();
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

        Course course = courseRepository.findById(courseId);
        courseRepository.addStudentToCourse(student, course);
        return course;
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course addCourse(String name, String description, Long teacherId) {
        Course course = new Course(name, description, teacherService.getById(teacherId));

        if (getCourses()
                .stream()
                .filter(c -> c.getName().equals(course.getName()))
                .findFirst()
                .orElse(null) != null) {
            return null;
        }

        return courseRepository.addCourse(course);
    }

    @Override
    public boolean deleteCourse(Long id) {
        return courseRepository.deleteCourse(id);
    }

    @Override
    public void editCourse(Long id, String name, String description, Long teacher) {
        Course course = courseRepository.findById(id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherService.getById(teacher));
    }

    @Override
    public List<Course> search(String term) {
        return courseRepository.search(term);
    }
}
