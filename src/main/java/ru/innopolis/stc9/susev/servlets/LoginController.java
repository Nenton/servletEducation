package ru.innopolis.stc9.susev.servlets;

import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.services.AuthService;
import ru.innopolis.stc9.susev.utils.ConstManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action != null && action.equals("logout")) {
            req.getSession().invalidate();
        }
        req.setAttribute("StringName", "String");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        if (authService.checkAuth(login, password)) {
            Role role = authService.getRole(login);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("login", login);

            switch (role.getId()) {
                case ConstManager.ADMIN_ROLE_ID:
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                    break;
                case ConstManager.STUDENT_ROLE_ID:
                case ConstManager.TEACHER_ROLE_ID:
                    resp.sendRedirect(req.getContextPath() + "/dashboard");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/login?errorMsg=roleNull");
                    break;
            }

        } else {
            resp.sendRedirect(req.getContextPath() + "/login?errorMsg=authErr");
        }
    }
}
