package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDao {
    String COLUMN_ID = "id";
    String COLUMN_ROLE = "role";

    /**
     * Insert role into DB
     */
    boolean addRole(Role role) throws SQLException;

    /**
     * Delete role from DB
     */
    boolean deleteRole(Role role) throws SQLException;

    /**
     * Delete role from DB by role id
     */
    boolean deleteRole(int id) throws SQLException;

    /**
     * Get role from DB by role id
     */
    @Nullable
    Role getRoleById(int id) throws SQLException;

    /**
     * Update role from DB
     */
    boolean updateRole(Role role) throws SQLException;

    /**
     * Get all roles from DB
     */
    List<Role> getRoles() throws SQLException;

}
