package ru.innopolis.stc9.susev.db.connection;

import java.sql.Connection;

/**
 * Connection for DB
 */
public interface ConnectionManager {
    Connection getConnection();
}
