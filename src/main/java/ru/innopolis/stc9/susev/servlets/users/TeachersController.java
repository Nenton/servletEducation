package ru.innopolis.stc9.susev.servlets.users;

import javax.servlet.annotation.WebServlet;

@WebServlet("/teachers")
public class TeachersController extends UsersAbstractController {
    @Override
    public int getRoleCreate() {
        return 4;
    }

    @Override
    public String getPathPage() {
        return "/teachers";
    }
}
