package ru.innopolis.stc9.susev.services;

import ru.innopolis.stc9.susev.db.dao.IRoleDao;
import ru.innopolis.stc9.susev.db.dao.RoleDao;
import ru.innopolis.stc9.susev.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleService {
    private IRoleDao roleDao = new RoleDao();

    public List<Role> getRoles(){
        try {
            return roleDao.getRoles();
        } catch (SQLException e) {
            // TODO: 12.05.2018 обработка
        }
        return null;
    }

    public boolean createRole(Role role){
        try {
            return roleDao.addRole(role);
        } catch (SQLException e) {
            // TODO: 12.05.2018 обработка
        }
        return false;
    }

    public boolean deleteRole(int id){
        try {
            return roleDao.deleteRole(id);
        } catch (SQLException e) {
            // TODO: 12.05.2018 обработка
        }
        return false;
    }
}
