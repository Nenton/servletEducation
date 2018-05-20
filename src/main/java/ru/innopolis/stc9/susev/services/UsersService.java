package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.dao.IRoleDao;
import ru.innopolis.stc9.susev.db.dao.IUserDao;
import ru.innopolis.stc9.susev.db.dao.RoleDao;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UsersService implements IUsersService {
    private IUserDao userDao = new UserDao();
    private IRoleDao roleDao = new RoleDao();

    @Override
    @Nullable
    public User getUserById(int id) {
        try {
            return userDao.getUserById(id);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователя по id - " + id, e);
            return null;
        }
    }

    @Override
    @Nullable
    public User getUserByLogin(String login) {
        try {
            return userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователя по login - " + login, e);
            return null;
        }
    }

    @Override
    public boolean deleteUserById(int idUser) {
        try {
            return userDao.deleteUserById(idUser);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления пользователя по id - " + idUser, e);
            return false;
        }
    }

    @Override
    public boolean createUser(User user) {
        try {
            return userDao.addUser(user);
        } catch (SQLException e) {
            logger.warn("Ошибка создания пользователя", e);
            return false;
        }
    }

    @Override
    @Nullable
    public List<User> getUsers() {
        try {
            return userDao.getUsers();
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователей", e);
            return null;
        }
    }

    @Override
    @Nullable
    public List<User> getUsers(int roleId) {
        try {
            return userDao.getUsers(roleId);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователей по id role - " + roleId, e);
            return null;
        }
    }

    @Override
    @Nullable
    public List<Role> getRoles() {
        try {
            return roleDao.getRoles();
        } catch (SQLException e) {
            logger.warn("Ошибка получения ролей", e);
            return null;
        }
    }
}
