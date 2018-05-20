package ru.innopolis.stc9.susev.servlets;

import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.services.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {
    private LessonService service = new LessonService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = service.getLessonsLast(10);
        if (lessons != null && lessons.size() != 0) {
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/pages/dashboard.jsp").forward(req, resp);
        } else {
            // TODO: 17.05.2018 404
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
