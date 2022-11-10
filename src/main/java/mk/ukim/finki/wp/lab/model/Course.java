package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Course implements Comparable<Course> {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    public Course(String name, String description, Teacher teacher) {
        this.courseId = new Random().nextLong();
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
    }

    @Override
    public int compareTo(Course o) {
        return name.compareTo(o.name);
    }
}
