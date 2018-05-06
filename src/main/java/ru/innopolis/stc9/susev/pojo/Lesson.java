package ru.innopolis.stc9.susev.pojo;

public class Lesson {
    private int id;

    private Subject subject;
    private int subjectId;
    private Student student;
    private int studentId;
    private Teacher teacher;
    private int teacherId;
    private Mark mark;
    private int markId;

    private boolean attendance;

    public Lesson(int id, Subject subject, int subjectId, Student student, int studentId,
                  Teacher teacher, int teacherId, Mark mark, int markId, boolean attendance) {
        this.id = id;
        this.subject = subject;
        this.subjectId = subjectId;
        this.student = student;
        this.studentId = studentId;
        this.teacher = teacher;
        this.teacherId = teacherId;
        this.mark = mark;
        this.markId = markId;
        this.attendance = attendance;
    }

    public Lesson(int id, int subjectId, int studentId, int teacherId, int markId, boolean attendance) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.markId = markId;
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

    public Student getStudent() {
        return student;
    }

    public int getStudentId() {
        return studentId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public Mark getMark() {
        return mark;
    }

    public int getMarkId() {
        return markId;
    }

    public boolean isAttendance() {
        return attendance;
    }
}
