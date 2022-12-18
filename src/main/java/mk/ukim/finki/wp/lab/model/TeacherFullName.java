package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class TeacherFullName implements Serializable {
    private String name;
    private String surname;

    public TeacherFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public TeacherFullName() {
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
