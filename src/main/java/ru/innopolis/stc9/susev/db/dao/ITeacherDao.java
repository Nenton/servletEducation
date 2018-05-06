package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Teacher;

public interface ITeacherDao {
    boolean addTeacher(Teacher teacher);

    boolean deleteTeacher(Teacher teacher);

    @Nullable
    Teacher getTeacherById(int id);

    boolean updateTeacher(Teacher teacher);
}
