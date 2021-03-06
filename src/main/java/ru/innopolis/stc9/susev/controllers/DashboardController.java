package ru.innopolis.stc9.susev.controllers;

import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.pojo.User;
import ru.innopolis.stc9.susev.services.ILessonService;
import ru.innopolis.stc9.susev.services.LessonService;
import ru.innopolis.stc9.susev.utils.ConstManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for all need compressed information for user
 */
@WebServlet(urlPatterns = {"/dashboard"})
public class DashboardController extends AbstractController {
    private ILessonService service = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet" + this.getClass().getName());
        String login = ((String) req.getSession().getAttribute("login"));
        List<Lesson> lessons = null;
        if (login != null && !login.isEmpty()) {
            User user = service.getUserByLogin(login);
            switch (user.getRole().getId()) {
                case ConstManager.STUDENT_ROLE_ID:
                    lessons = service.getLessonsByStudentId(user.getId(), 10);
                    break;
                case ConstManager.TEACHER_ROLE_ID:
                    lessons = service.getLessonsByTeacherId(user.getId(), 10);
                    break;
                case ConstManager.ADMIN_ROLE_ID:
                    lessons = service.getLessonsLast(10);
                    break;
                default:
                    resp.sendRedirect("/login?errorMsg=noAccess");
                    break;
            }
        }
        if (lessons != null) {
            req.setAttribute("lessons", lessons);
        }
        req.getRequestDispatcher("/pages/dashboard.jsp").forward(req, resp);
    }
}
