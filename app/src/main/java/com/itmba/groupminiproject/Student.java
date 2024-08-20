package com.itmba.groupminiproject;

public class Student {

    String name, surname, phoneNUmber, studntNumber = "";

    private Student() {
    }


    public Student(String name, String surname, String phoneNUmber, String studntNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNUmber = phoneNUmber;
        this.studntNumber = studntNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public String getStudntNumber() {
        return studntNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
    }

    public void setStudntNumber(String studntNumber) {
        this.studntNumber = studntNumber;
    }

}
