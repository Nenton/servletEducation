package ru.innopolis.stc9.susev.servlets.users;

public interface IUserController {
    /**
     * Get current role id
     */
    int getRoleCreate();

    /**
     * Get path page for current role
     */
    String getPathPage();
}
