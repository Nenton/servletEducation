package ru.innopolis.stc9.susev.servlets.users;

import javax.servlet.annotation.WebServlet;

@WebServlet("/students")
public class StudentsServlet extends UsersAbstractServlet {

    @Override
    public int getRoleCreate() {
        return 3;
    }

    @Override
    public String getPathPage() {
        return "/students";
    }
}
