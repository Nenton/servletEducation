package ru.innopolis.stc9.susev.controllers.users;

import javax.servlet.annotation.WebServlet;

@WebServlet("/students")
public class StudentsController extends UsersAbstractController {

    @Override
    public int getRoleCreate() {
        return 3;
    }

    @Override
    public String getPathPage() {
        return "/students";
    }
}
