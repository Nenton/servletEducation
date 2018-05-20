package ru.innopolis.stc9.susev.db.dao;


import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.connection.ConnectionManager;
import ru.innopolis.stc9.susev.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.susev.pojo.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao implements ISubjectDao {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into subjects(subject_name) values (?)");
            statement.setString(1, subject.getName());
            return statement.execute();
        }
    }

    @Override
    public boolean deleteSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from subjects where id = ?");
            statement.setInt(1, subject.getId());
            return statement.execute();
        }
    }

    @Override
    @Nullable
    public Subject getSubjectById(int id) throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subjects WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getSubjectFromDb(resultSet);
            }
            return null;
        }
    }

    @Override
    public List<Subject> getSubjects() throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            List<Subject> subjects = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subjects");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subjects.add(getSubjectFromDb(resultSet));
            }
            return subjects;
        }
    }

    @Override
    public boolean updateSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("update subjects set subject_name = ? where id = ?");
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getId());
            statement.executeUpdate();
            return true;
        }
    }

    /**
     * Get subject from DB
     */
    private Subject getSubjectFromDb(ResultSet set) throws SQLException {
        return new Subject(set.getInt(COLUMN_ID),
                set.getString(COLUMN_SUBJECT_NAME));
    }
}
