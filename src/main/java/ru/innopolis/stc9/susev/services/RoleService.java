package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.dao.IRoleDao;
import ru.innopolis.stc9.susev.db.dao.RoleDao;
import ru.innopolis.stc9.susev.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleService implements IRoleService {
    private IRoleDao roleDao = new RoleDao();

    @Override
    @Nullable
    public List<Role> getRoles() {
        try {
            return roleDao.getRoles();
        } catch (SQLException e) {
            logger.warn("Ошибка получения всех ролей", e);
            return null;
        }
    }

    @Override
    @Nullable
    public boolean createRole(Role role) {
        if (role == null) {
            return false;
        }
        try {
            return roleDao.addRole(role);
        } catch (SQLException e) {
            logger.warn("Ошибка создания роли", e);
            return false;
        }
    }

    @Override
    @Nullable
    public boolean deleteRole(int id) {
        if (id == 0) {
            return false;
        }
        try {
            return roleDao.deleteRole(id);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления роли", e);
            return false;
        }
    }
}
