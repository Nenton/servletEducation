package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.dao.IRoleDao;
import ru.innopolis.stc9.susev.db.dao.IUserDao;
import ru.innopolis.stc9.susev.db.dao.RoleDao;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;

public class AuthService implements IAuthService {
    private IUserDao userDao = new UserDao();
    private IRoleDao roleDao = new RoleDao();

    @Override
    public boolean checkAuth(String login, String password) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка проверки авторизации", e);
        }
        return user != null && user.getPasswordHash().equals(password);
    }

    @Override
    @Nullable
    public Role getRoleByUserLogin(String login) {
        Role role = null;
        try {
            role = roleDao.getRoleByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка проверки авторизации", e);
        }
        return role;
    }
}
