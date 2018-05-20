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
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Nullable
    @Override
    public User getUserByLogin(String login) throws SQLException {
        if (login == null || login.isEmpty()) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                return getUserFromDb(resultSet, role);
            }
            return null;
        }
    }


    @Nullable
    @Override
    public User getUserById(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                return getUserFromDb(resultSet, role);
            }
            return null;
        }
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into users(login, password, role, fullName) " +
                    "values (?, ?, ?, ?)");
            setParamsIntoStatement(statement, user);
            return statement.execute();
        }
    }


    @Override
    public boolean deleteUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
            statement.setInt(1, user.getId());
            return statement.execute();
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users " +
                    "set login = ?, password = ?, role = ?, fullName = ?" +
                    "where id = ?");
            setParamsIntoStatement(statement, user);
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            return true;
        }
    }


    @Override
    public boolean deleteUserById(int idUser) throws SQLException {
        if (idUser == 0) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
            statement.setInt(1, idUser);
            return statement.execute();
        }
    }

    @Override
    public List<User> getUsers(int roleId) throws SQLException {
        if (roleId == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            List<User> result = new ArrayList<>();
            String sql = "select * from users where users.role = " + roleId + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                result.add(getUserFromDb(resultSet, role));
            }
            return result;
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            List<User> result = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IRoleDao roleDao = new RoleDao();
                Role role = roleDao.getRoleById(resultSet.getInt(COLUMN_ROLE));
                result.add(getUserFromDb(resultSet, role));
            }
            return result;
        }
    }

    /**
     * Get user from DB
     */
    private User getUserFromDb(ResultSet resultSet, Role role) throws SQLException {
        return new User(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_LOGIN),
                resultSet.getString(COLUMN_PASSWORD), role, resultSet.getString(COLUMN_FULL_NAME));
    }

    /**
     * Set params from user into statement
     */
    private void setParamsIntoStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPasswordHash());
        statement.setInt(3, user.getRole().getId());
        statement.setString(4, user.getFullName());
    }
}