package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.connection.ConnectionManager;
import ru.innopolis.stc9.susev.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IUserDao {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_FULL_NAME = "fullName";

    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Nullable
    @Override
    public User getUserByLogin(String login) throws SQLException {
        User result = null;
        Connection connection = ConnectionManagerJDBCImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login=?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Role role = new RoleDao().getRoleById(resultSet.getInt(COLUMN_ROLE));
            result = new User(resultSet.getInt(COLUMN_ID),
                    resultSet.getString(COLUMN_LOGIN),
                    resultSet.getString(COLUMN_PASSWORD),
                    role,
                    resultSet.getString(COLUMN_FULL_NAME));
        }
        connection.close();
        return result;
    }

    @Nullable
    @Override
    public User getUserById(int id) throws SQLException {
        User result = null;
        Connection connection = ConnectionManagerJDBCImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Role role = new RoleDao().getRoleById(resultSet.getInt(COLUMN_ROLE));
            result = new User(resultSet.getInt(COLUMN_ID),
                    resultSet.getString(COLUMN_LOGIN),
                    resultSet.getString(COLUMN_PASSWORD),
                    role,
                    resultSet.getString(COLUMN_FULL_NAME));
        }
        connection.close();
        return result;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("insert into users(id, login, password, role, fullName) " +
                "values (?, ?, ?, ?, ?)");
        statement.setInt(1, user.getId());
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getPasswordHash());
        statement.setInt(4, user.getRole().getId());
        statement.setString(5, user.getFullName());

        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("delete from users where id = ?");
        statement.setInt(1, user.getId());
        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("update users " +
                "set login = ?, password = ?, role = ?, fullName = ?" +
                "where id = ?");
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPasswordHash());
        statement.setInt(3, user.getRole().getId());
        statement.setString(4, user.getFullName());
        statement.setInt(5, user.getId());

        statement.executeUpdate();
        connection.close();
        return true;
    }

    @Override
    public Role getRoleByLogin(String login) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("select r.id, r.role from users " +
                "inner join role r on r.id = users.role " +
                "where users.login = ?");
        statement.setString(1, login);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Role(
                    set.getInt(COLUMN_ID),
                    set.getString(COLUMN_ROLE)
            );
        }
        connection.close();
        return null;
    }

    @Override
    public List<User> getStudents() throws SQLException {
        return null;
    }

    @Override
    public List<User> getTeachers() throws SQLException {
        return null;
    }
}
