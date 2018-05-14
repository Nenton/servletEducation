package ru.innopolis.stc9.susev.db.dao;

import ru.innopolis.stc9.susev.db.connection.ConnectionManager;
import ru.innopolis.stc9.susev.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.susev.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao implements IRoleDao {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ROLE = "role";

    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addRole(Role role) throws SQLException {
        if (role == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("insert into role(id, role) values (?, ?)");
        statement.setInt(1, role.getId());
        statement.setString(2, role.getRole());

        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean deleteRole(Role role) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("delete from role where id = ?");
        statement.setInt(1, role.getId());
        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public Role getRoleById(int id) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("select * from role where id = ?");
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Role(
                    set.getInt(COLUMN_ID),
                    set.getString(COLUMN_ROLE)
            );
        }

        connection.close();
        return null;
    }

    @Override
    public boolean updateRole(Role role) throws SQLException {
        if (role == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("update role " +
                "set role = ?" +
                "where id = ?");
        statement.setString(1, role.getRole());
        statement.setInt(2, role.getId());

        statement.executeUpdate();
        connection.close();
        return true;
    }
}
