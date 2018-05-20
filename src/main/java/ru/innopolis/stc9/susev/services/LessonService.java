package ru.innopolis.stc9.susev.services;

import ru.innopolis.stc9.susev.db.dao.LessonDao;
import ru.innopolis.stc9.susev.db.dao.SubjectDao;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.pojo.Subject;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class LessonService {
    LessonDao lessonDao = new LessonDao();
    SubjectDao subjectDao = new SubjectDao();
    UserDao userDao = new UserDao();

    public User getUserByLogin(String login) {
        try {
            return userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Subject> getSubjects() {
        try {
            return subjectDao.getSubjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getStudents() {
        try {
            return userDao.getUsers(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getTeachers() {
        try {
            return userDao.getUsers(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getLessonsBySubject(int id) {
        try {
            return lessonDao.getLessonsBySubject(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getLessonsByTeacherId(int id, int count) {
        try {
            return lessonDao.getLessonsByTeacher(id, count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getLessonsByStudentId(int id, int count) {
        try {
            return lessonDao.getLessonsByStudent(id, count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getLessonsLast(int count) {
        try {
            return lessonDao.getLessons(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createLesson(Lesson lesson) {
        try {
            return lessonDao.addLesson(lesson);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLessonById(int idLesson) {
        try {
            return lessonDao.deleteLessonById(idLesson);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
