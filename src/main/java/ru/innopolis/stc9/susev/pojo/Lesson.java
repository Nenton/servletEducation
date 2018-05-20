package ru.innopolis.stc9.susev.pojo;

public class Lesson {
    private int id;

    private Subject subject;
    private int subjectId;
    private User student;
    private int studentId;
    private User teacher;
    private int teacherId;
    private int mark;

    private boolean attendance;

    public Lesson(int id, Subject subject, int subjectId, User student, int studentId,
                  User teacher, int teacherId, int mark, boolean attendance) {
        this.id = id;
        this.subject = subject;
        this.subjectId = subjectId;
        this.student = student;
        this.studentId = studentId;
        this.teacher = teacher;
        this.teacherId = teacherId;
        this.mark = mark;
        this.attendance = attendance;
    }

    public Lesson(int id, int subjectId, int studentId, int teacherId, int mark, boolean attendance) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.mark = mark;
        this.attendance = attendance;
    }

    public Lesson(int subjectId, int studentId, int teacherId, int mark, boolean attendance) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.mark = mark;
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public User getStudent() {
        return student;
    }

    public int getStudentId() {
        return studentId;
    }

    public User getTeacher() {
        return teacher;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getMark() {
        return mark;
    }

    public boolean isAttendance() {
        return attendance;
    }
}
