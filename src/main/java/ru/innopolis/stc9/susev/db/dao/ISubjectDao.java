package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Subject;

import java.sql.SQLException;
import java.util.List;

public interface ISubjectDao {
    String COLUMN_ID = "id";
    String COLUMN_SUBJECT_NAME = "subject_name";

    /**
     * Insert subject into DB
     */
    boolean addSubject(Subject subject) throws SQLException;

    /**
     * Delete subject from DB
     */
    boolean deleteSubject(Subject subject) throws SQLException;

    /**
     * Get subject by id
     */
    @Nullable
    Subject getSubjectById(int id) throws SQLException;

    /**
     * Update subject from DB
     */
    boolean updateSubject(Subject subject) throws SQLException;

    /**
     * Get all subjects from DB
     */
    List<Subject> getSubjects() throws SQLException;
}
