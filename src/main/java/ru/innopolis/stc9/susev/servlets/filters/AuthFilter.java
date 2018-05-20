package ru.innopolis.stc9.susev.servlets.filters;

import ru.innopolis.stc9.susev.pojo.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for users. Access is granted only registered users
 */
@WebFilter(urlPatterns = {"/teachers", "/students", "/lessons", "/users", "/roles"})
public class AuthFilter extends AbstractFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        Role role = (Role) httpSession.getAttribute("role");
        Object login = httpSession.getAttribute("login");
        boolean access = false;
        if (role != null) {
            switch (role.getId()) {
                case 1:
                case 3:
                case 4:
                    access = true;
                default:
                    break;
            }
        }
        if (login != null && access) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?errorMsg=noAccess");
        }
    }
}
