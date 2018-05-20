package ru.innopolis.stc9.susev.servlets;

import ru.innopolis.stc9.susev.pojo.User;
import ru.innopolis.stc9.susev.services.IUsersService;
import ru.innopolis.stc9.susev.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/*")
public class AnyServlet extends HttpServlet {
    private IUsersService service = new UsersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("any");
        try {
            int idUser = Integer.parseInt(req.getPathInfo().substring(1));
            User userById = service.getUserById(idUser);
            req.setAttribute("user", userById);
            req.getRequestDispatcher("../pages/user.jsp");
        } catch (NumberFormatException e) {

        }
    }
}
