package ru.innopolis.stc9.susev.services;

import ru.innopolis.stc9.susev.db.dao.LessonDao;
import ru.innopolis.stc9.susev.pojo.Lesson;

import java.sql.SQLException;
import java.util.List;

public class LessonService {
    LessonDao lessonDao = new LessonDao();

    public List<Lesson> getLessonsBySubject(int id) {
        try {
            lessonDao.getLessonsBySubject(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getLessonsByTeacher(int id) {
        try {
            return lessonDao.getLessonsByTeacher(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getLessonsByStudent(int id) {
        try {
            return lessonDao.getLessonsByStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
