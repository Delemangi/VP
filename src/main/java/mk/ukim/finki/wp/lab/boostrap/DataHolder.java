package mk.ukim.finki.wp.lab.boostrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> students;
    public static List<Teacher> teachers;
    public static List<Course> courses;

    @PostConstruct
    public void init() {
        students = new ArrayList<>(5);
        teachers = new ArrayList<>(5);
        courses = new ArrayList<>(5);

        students.add(new Student("student1", "Student", "Student 1", "S"));
        students.add(new Student("student2", "Student", "Student 2", "S"));
        students.add(new Student("student3", "Student", "Student 3", "S"));

        teachers.add(new Teacher("Teacher 1", "T"));
        teachers.add(new Teacher("Teacher 2", "T"));
        teachers.add(new Teacher("Teacher 3", "T"));

        courses.add(new Course("Course 1", "Description 1", teachers.get(0), Type.ELECTIVE));
        courses.add(new Course("Course 2", "Description 2", teachers.get(1), Type.MANDATORY));
        courses.add(new Course("Course 3", "Description 3", teachers.get(2), Type.WINTER));
    }
}
