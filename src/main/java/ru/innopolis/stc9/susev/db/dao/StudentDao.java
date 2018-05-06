package ru.innopolis.stc9.susev.db.dao;

import ru.innopolis.stc9.susev.pojo.Student;

public class StudentDao implements IStudentDao {

    @Override
    public boolean addStudent(Student student) {
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        return false;
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }
}
