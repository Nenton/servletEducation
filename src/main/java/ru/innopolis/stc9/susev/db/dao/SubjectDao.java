package ru.innopolis.stc9.susev.db.dao;


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
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBJECT_NAME = "subject_name";

    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("insert into subjects(id, subject_name) values (?, ?)");
        statement.setInt(1, subject.getId());
        statement.setString(2, subject.getName());

        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean deleteSubject(Subject subject) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("delete from subjects where id = ?");
        statement.setInt(1, subject.getId());
        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public Subject getSubjectById(int id) throws SQLException {
        Subject subject = null;
        Connection connection = ConnectionManagerJDBCImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subjects WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            subject = new Subject(resultSet.getInt(COLUMN_ID),
                    resultSet.getString(COLUMN_SUBJECT_NAME));
        }
        connection.close();
        return subject;
    }

    public List<Subject> getSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        Connection connection = ConnectionManagerJDBCImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subjects");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            subjects.add(new Subject(resultSet.getInt(COLUMN_ID),
                    resultSet.getString(COLUMN_SUBJECT_NAME)));
        }
        connection.close();
        return subjects;
    }

    @Override
    public boolean updateSubject(Subject subject) throws SQLException {
        if (subject == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("update subjects " +
                "set subject_name = ?" +
                "where id = ?");
        statement.setString(1, subject.getName());
        statement.setInt(2, subject.getId());

        statement.executeUpdate();
        connection.close();
        return true;
    }
}
