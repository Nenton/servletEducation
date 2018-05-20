package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDao {
    boolean addRole(Role role) throws SQLException;

    boolean deleteRole(Role role) throws SQLException;
    boolean deleteRole(int id) throws SQLException;

    @Nullable
    Role getRoleById(int id) throws SQLException;

    boolean updateRole(Role role) throws SQLException;

    List<Role> getRoles() throws SQLException;

}
