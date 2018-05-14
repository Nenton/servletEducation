package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    @Nullable
    User getUserByLogin(String login) throws SQLException;

    @Nullable
    User getUserById(int idUser) throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean deleteUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    Role getRoleByLogin(String login) throws SQLException;

    List<User> getStudents() throws SQLException;

    List<User> getTeachers() throws SQLException;
}
