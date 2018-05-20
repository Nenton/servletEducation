package ru.innopolis.stc9.susev.servlets;

import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.services.AuthService;
import ru.innopolis.stc9.susev.services.IAuthService;
import ru.innopolis.stc9.susev.utils.ConstManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for login operations
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    private IAuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String errorMsg = req.getParameter("errorMsg");
        if (errorMsg != null && errorMsg.equals("noAccess")) {

        }

        if (errorMsg != null && errorMsg.equals("authErr")) {
            req.setAttribute("errorMsg", "String");
        }
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("exit");
        if (action != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/");
        } else {
            String login = req.getParameter("userName");
            String password = req.getParameter("userPassword");
            if (authService.checkAuth(login, password)) {
                Role role = authService.getRoleByUserLogin(login);
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("login", login);

                switch (role.getId()) {
                    case ConstManager.ADMIN_ROLE_ID:
                    case ConstManager.STUDENT_ROLE_ID:
                    case ConstManager.TEACHER_ROLE_ID:
                        resp.sendRedirect("/dashboard");
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
}
