package ru.innopolis.stc9.susev.servlets;

import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.pojo.Subject;
import ru.innopolis.stc9.susev.pojo.User;
import ru.innopolis.stc9.susev.services.AuthService;
import ru.innopolis.stc9.susev.services.ILessonService;
import ru.innopolis.stc9.susev.services.LessonService;
import ru.innopolis.stc9.susev.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Controller for show lessons for student or teacher
 */
@WebServlet(urlPatterns = {"/lessons"})
public class LessonsController extends HttpServlet {
    private ILessonService service = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = ((String) req.getSession().getAttribute("login"));
        List<Lesson> lessons = null;
        if (login != null && !login.isEmpty()) {
            User userByLogin = service.getUserByLogin(login);
            switch (userByLogin.getRole().getId()) {
                case 1:
                    lessons = service.getLessonsLast(100);
                    break;
                case 3:
                    lessons = service.getLessonsByStudentId(userByLogin.getId(), 100);
                    break;
                case 4:
                    lessons = service.getLessonsByTeacherId(userByLogin.getId(), 100);
                    break;
                default:
                    break;
            }
        }
        List<Integer> marks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            marks.add(i);
        }
        List<User> teachers = service.getTeachers();
        List<User> students = service.getStudents();
        List<Subject> subjects = service.getSubjects();
// TODO: 20.05.2018 mapping
        req.setAttribute("marks", marks);
        req.setAttribute("subjects", subjects);
        req.setAttribute("students", students);
        req.setAttribute("teachers", teachers);
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/pages/lessons.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        try {
            if (req.getParameter("deleteBtn") != null) {
                int idLesson = Integer.parseInt(req.getParameter("lessonId"));
                service.deleteLessonById(idLesson);
                doGet(req, resp);
            }
            if (req.getParameter("createLesson") != null) {
                Lesson lesson = null;
                int subject = Integer.parseInt(req.getParameter("subject"));
                int student = Integer.parseInt(req.getParameter("student"));
                int mark = Integer.parseInt(req.getParameter("mark"));
                String[] selected = req.getParameterValues("attendance");
                if (req.getParameter("teacher") == null) {
                    String login = (String) req.getSession().getAttribute("login");
                    User userByLogin = service.getUserByLogin(login);
                    lesson = new Lesson(subject, student, userByLogin.getId(), mark, selected != null && selected.length != 0);
                } else {
                    int teacher = Integer.parseInt(req.getParameter("teacher"));
                    lesson = new Lesson(subject, student, teacher, mark, selected != null && selected.length != 0);
                }
                service.createLesson(lesson);
                doGet(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            // TODO: 20.05.2018 Страница с ошибкой
        }
        // TODO: 19.05.2018 создание урока
    }
}
