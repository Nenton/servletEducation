package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Student;

public interface IStudentDao {
    boolean addStudent(Student student);

    boolean deleteStudent(Student student);

    @Nullable
    Student getStudentById(int id);

    boolean updateStudent(Student student);
}
