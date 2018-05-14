package ru.innopolis.stc9.susev.servlets.filters;

import ru.innopolis.stc9.susev.pojo.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/teachers", "/students", "/lessons"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        Role role = (Role) httpSession.getAttribute("role");
        Object login = httpSession.getAttribute("login");
        if (login != null && role != null && role.getRole().equals("admin")) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?errorMsg=noAuth");
        }
    }

    @Override
    public void destroy() {

    }
}
