package ru.innopolis.stc9.susev.pojo;

public class Student {
    private int id;
    private String fullName;
    private int course;

    public Student(int id, String fullName, int course) {
        this.id = id;
        this.fullName = fullName;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
