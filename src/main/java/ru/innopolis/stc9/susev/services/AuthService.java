package ru.innopolis.stc9.susev.services;

import ru.innopolis.stc9.susev.db.dao.IRoleDao;
import ru.innopolis.stc9.susev.db.dao.IUserDao;
import ru.innopolis.stc9.susev.db.dao.RoleDao;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;

public class AuthService {
    private IUserDao userDao = new UserDao();
    private IRoleDao roleDao = new RoleDao();

    public boolean checkAuth(String login, String password) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            // TODO: 12.05.2018 обработка
        }
        return user != null && user.getPasswordHash().equals(password);
    }

    public Role getRole(String login) {
        Role role = null;
        try {
            role = roleDao.getRoleByLogin(login);
        } catch (SQLException e) {
            // TODO: 13.05.2018 обработка
        }
        return role;
    }
}
