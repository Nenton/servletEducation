package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersService {
    private UserDao userDao = new UserDao();

    @Nullable
    public List<User> getStudents() {
        List<User> users = null;
//        try {
//            users = userDao.getStudents();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        users = new ArrayList<>();
        users.add(new User(1, "Login", "Password", new Role(1, "Student"), "Sus"));
        return users;
    }

    @Nullable
    public List<User> getTeachers() {
        List<User> users = null;
        try {
            users = userDao.getTeachers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
