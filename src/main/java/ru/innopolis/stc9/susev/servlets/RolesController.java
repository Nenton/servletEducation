package ru.innopolis.stc9.susev.servlets;


import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.services.IRoleService;
import ru.innopolis.stc9.susev.services.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for show roles
 */
@WebServlet("/roles")
public class RolesController extends HttpServlet {
    private IRoleService service = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<Role> roles = service.getRoles();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/pages/roles.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            if (req.getParameter("deleteRole") != null) {
                int roleId = Integer.parseInt(req.getParameter("roleId"));
                service.deleteRole(roleId);
                doGet(req, resp);
            }
            if (req.getParameter("addRole") != null) {

                String roleName = req.getParameter("nameRole");
                Role role = new Role(roleName);

                service.createRole(role);
                doGet(req, resp);
            }

        } catch (Exception e) {
            // TODO: 20.05.2018 Страница с ошибкой
        }
        // TODO: 19.05.2018 создание урока
    }
}
