package controllers.filters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.innopolis.stc9.susev.controllers.filters.AdminFilter;
import ru.innopolis.stc9.susev.pojo.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFIlterTest {
    private AdminFilter adminFilter;

    @Before
    public void before() {
        adminFilter = new AdminFilter();
    }

    @Test
    public void doFilterTest() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("role")).thenReturn(new Role(1, "admin"));
        Mockito.when(session.getAttribute("login")).thenReturn("login");

        Mockito.when(request.getSession()).thenReturn(session);
        methodAccess(request, resp, chain);
    }

    void methodAccess(HttpServletRequest request, HttpServletResponse resp, FilterChain chain) {

        try {
            adminFilter.doFilter(request, resp, chain);
        } catch (IOException | ServletException e) {
            Assert.fail();
        }
        try {
            Mockito.verify(chain, Mockito.times(1)).doFilter(request, resp);
        } catch (IOException | ServletException e) {
            Assert.fail();
        }
    }

    @Test
    public void doFilterTestLoginEmpty() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("role")).thenReturn(new Role(1, "admin"));
        Mockito.when(session.getAttribute("login")).thenReturn("");

        Mockito.when(request.getSession()).thenReturn(session);
        methodCheck(request, resp, chain);
    }

    @Test
    public void doFilterTestRoleNotAdmin() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("role")).thenReturn(new Role(3, "student"));
        Mockito.when(session.getAttribute("login")).thenReturn("login");
        Mockito.when(request.getSession()).thenReturn(session);

        methodCheck(request, resp, chain);

    }

    @Test
    public void doFilterTestRoleNotLogin() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("role")).thenReturn(new Role(3, "student"));
        Mockito.when(session.getAttribute("login")).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(session);

        methodCheck(request, resp, chain);
    }

    @Test
    public void doFilterTestRoleNotRole() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("role")).thenReturn(null);
        Mockito.when(session.getAttribute("login")).thenReturn("login");
        Mockito.when(request.getSession()).thenReturn(session);

        methodCheck(request, resp, chain);
    }

    @Test
    public void doFilterTestRoleNotRoleNotLogin() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(session.getAttribute("role")).thenReturn(null);
        Mockito.when(session.getAttribute("login")).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(session);

        methodCheck(request, resp, chain);
    }

    private void methodCheck(HttpServletRequest request, HttpServletResponse resp, FilterChain chain) {
        try {
            adminFilter.doFilter(request, resp, chain);
        } catch (IOException | ServletException e) {
            Assert.fail();
        }
        try {
            Mockito.verify(resp, Mockito.times(1)).sendRedirect(request.getContextPath() + "/login?errorMsg=noAccess");
        } catch (IOException e) {
            Assert.fail();
        }
    }
}
