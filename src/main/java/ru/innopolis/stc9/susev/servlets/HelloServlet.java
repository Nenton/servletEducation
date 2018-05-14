package ru.innopolis.stc9.susev.servlets;

import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.services.LessonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HelloServlet extends HttpServlet {
    private LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String studentId = req.getParameter("student");
        if (studentId != null && !studentId.isEmpty()) {
            int i = Integer.parseInt(studentId);
            List<Lesson> lessonsByStudent = lessonService.getLessonsByStudent(i);
            for (Lesson lesson : lessonsByStudent) {
                String lessonString = "Lesson ID:" + lesson.getId() + ", " +
                        "subject:" + lesson.getSubject().getName() + ", " +
                        "student name:" + lesson.getStudent().getFullName() + ", " +
                        "teacher name:" + lesson.getTeacher().getFullName() + ", " +
                        "mark:" + lesson.getMark() + ", " +
                        "attendance:" + lesson.isAttendance();
                resp.getWriter().println(lessonString);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
