package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.dao.IRoleDao;
import ru.innopolis.stc9.susev.db.dao.IUserDao;
import ru.innopolis.stc9.susev.db.dao.RoleDao;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersService {
    private UserDao userDao = new UserDao();
    private IRoleDao roleDao = new RoleDao();

    @Nullable
    public User getUserById(String id) {
        User user = null;
        try {
            user = userDao.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Nullable
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean deleteUserById(int idUser) {
        try {
            return userDao.deleteUserById(idUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createUser(User user) {
        try {
            return userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {
        List<User> users = null;
        try {
            users = userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getUsers(int roleId) {
        List<User> users = null;
        try {
            users = userDao.getUsers(roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Role> getRoles() {
        List<Role> roles = null;
        try {
            roles = roleDao.getRoles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
