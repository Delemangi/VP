package mk.ukim.finki.wp.lab.boostrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> students;
    public static List<Course> courses;

    @PostConstruct
    public void init() {
        students = new ArrayList<>(5);
        courses = new ArrayList<>(5);

        students.add(new Student("student1", "Student", "Student 1", "S"));
        students.add(new Student("student2", "Student", "Student 2", "S"));
        students.add(new Student("student3", "Student", "Student 3", "S"));

        courses.add(new Course(1L, "Course 1", "Course description 1"));
        courses.add(new Course(2L, "Course 2", "Course description 2"));
        courses.add(new Course(3L, "Course 3", "Course description 3"));
    }
}
