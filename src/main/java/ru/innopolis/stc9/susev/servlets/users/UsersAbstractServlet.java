package ru.innopolis.stc9.susev.servlets.users;

import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;
import ru.innopolis.stc9.susev.services.IUsersService;
import ru.innopolis.stc9.susev.services.UsersService;
import ru.innopolis.stc9.susev.servlets.IUserServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class UsersAbstractServlet extends HttpServlet implements IUserServlet {
    private IUsersService service = new UsersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<User> users = null;
        int roleId = getRoleCreate();
        if (roleId == 0) {
            users = service.getUsers();
        } else {
            users = service.getUsers(roleId);
            req.setAttribute("createRole", getRoleCreate());
        }
        if (users != null) {
            req.setAttribute("users", users);
        }
        List<Role> roles = service.getRoles();
        req.setAttribute("roles", roles);
        req.setAttribute("path", getPathPage());
        req.getRequestDispatcher("/pages/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getParameter("deleteBtn") != null) {
            int idUser = Integer.parseInt(req.getParameter("userId"));
            service.deleteUserById(idUser);
            resp.sendRedirect(getPathPage());
        }

        if (req.getParameter("createUser") != null) {
            String name = req.getParameter("nameUser");
            String login = req.getParameter("loginUser");
            String password = req.getParameter("passwordUser");
            int roleId;
            if (req.getParameter("roleIdUser") != null){
                roleId = Integer.parseInt(req.getParameter("roleIdUser"));
            } else {
                roleId = Integer.parseInt(req.getParameter("role"));
            }
            User user = new User(name, login, password, roleId);
            service.createUser(user);
            resp.sendRedirect(getPathPage());
        }
    }
}
