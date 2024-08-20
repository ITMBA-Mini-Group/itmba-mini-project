package com.example.groupminiproject;

import androidx.annotation.NonNull;

public class Student {
    private long id;
    private String studentNumber;
    private String name;
    private String surname;
    private String phone;

    public Student(long id, String studentNumber, String name, String surname, String phone) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return studentNumber + ": " + name + " " + surname + " - " + phone;
    }
}
