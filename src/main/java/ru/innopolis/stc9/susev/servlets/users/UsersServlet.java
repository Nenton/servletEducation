package ru.innopolis.stc9.susev.servlets.users;

import javax.servlet.annotation.WebServlet;

@WebServlet("/users")
public class UsersServlet extends UsersAbstractServlet {

    @Override
    public int getRoleCreate() {
        return 0;
    }

    @Override
    public String getPathPage() {
        return "/users";
    }
}
