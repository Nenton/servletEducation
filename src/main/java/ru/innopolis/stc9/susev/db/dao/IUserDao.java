package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    String COLUMN_ID = "id";
    String COLUMN_LOGIN = "login";
    String COLUMN_PASSWORD = "password";
    String COLUMN_ROLE = "role";
    String COLUMN_FULL_NAME = "fullName";

    /**
     * Get user from DB by user login
     */
    @Nullable
    User getUserByLogin(String login) throws SQLException;

    /**
     * Get user from DB by user id
     */
    @Nullable
    User getUserById(int id) throws SQLException;

    /**
     * Insert user into DB
     */
    boolean addUser(User user) throws SQLException;

    /**
     * Delete user from DB
     */
    boolean deleteUser(User user) throws SQLException;

    /**
     * Update user from DB
     */
    boolean updateUser(User user) throws SQLException;

    /**
     * Delete user from DB by user id
     */
    boolean deleteUserById(int idUser) throws SQLException;

    /**
     * Get users from DB by role id
     */
    List<User> getUsers(int roleId) throws SQLException;

    /**
     * Get all users from DB
     */
    List<User> getUsers() throws SQLException;
}
