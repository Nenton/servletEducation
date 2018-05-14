package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Subject;

import java.sql.SQLException;

public interface ISubjectDao {
    boolean addSubject(Subject subject) throws SQLException;

    boolean deleteSubject(Subject subject) throws SQLException;

    @Nullable
    Subject getSubjectById(int id) throws SQLException;

    boolean updateSubject(Subject subject) throws SQLException;
}
