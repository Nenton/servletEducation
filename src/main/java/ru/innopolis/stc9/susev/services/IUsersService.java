package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import org.apache.log4j.Logger;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;

import java.util.List;

public interface IUsersService {
    Logger logger = Logger.getLogger(IUsersService.class);

    /**
     * Get user entity by user id
     */
    @Nullable
    User getUserById(int id);

    /**
     * Get user entity by user login
     */
    @Nullable
    User getUserByLogin(String login);

    /**
     * Delete user entity by user id
     */
    boolean deleteUserById(int idUser);

    /**
     * Create user entity
     */
    boolean createUser(User user);

    /**
     * Get all user
     */
    @Nullable
    List<User> getUsers();

    /**
     * Get users with choose role id
     */
    @Nullable
    List<User> getUsers(int roleId);

    /**
     * Get all roles
     */
    @Nullable
    List<Role> getRoles();
}
