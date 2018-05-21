package ru.innopolis.stc9.susev.controllers.users;

import com.sun.istack.internal.NotNull;

import javax.servlet.annotation.WebServlet;

@WebServlet("/users")
public class UsersController extends UsersAbstractController {

    @Override
    public int getRoleCreate() {
        return 0;
    }

    @Override
    @NotNull
    public String getPathPage() {
        return "/users";
    }
}
