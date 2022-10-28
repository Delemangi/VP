package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Student {
    private final String username;
    private final String password;
    private final String name;
    private final String surname;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
