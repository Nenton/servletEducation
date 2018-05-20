package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.dao.*;
import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.pojo.Subject;
import ru.innopolis.stc9.susev.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class LessonService implements ILessonService {
    private ILessonDao lessonDao = new LessonDao();
    private ISubjectDao subjectDao = new SubjectDao();
    private IUserDao userDao = new UserDao();

    @Override
    @Nullable
    public User getUserByLogin(String login) {
        if (login == null || login.isEmpty()) {
            return null;
        }
        try {
            return userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.warn("Ошибка получения пользователя", e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<Subject> getSubjects() {
        try {
            return subjectDao.getSubjects();
        } catch (SQLException e) {
            logger.warn("Ошибка получения предметов", e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<User> getStudents() {
        try {
            return userDao.getUsers(3);
        } catch (SQLException e) {
            logger.warn("Ошибка получения студентов", e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<User> getTeachers() {
        try {
            return userDao.getUsers(4);
        } catch (SQLException e) {
            logger.warn("Ошибка получения учителей", e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<Lesson> getLessonsBySubject(int id, int count) {
        if (id == 0 || count == 0) {
            return null;
        }
        try {
            return lessonDao.getLessonsBySubject(id, count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения занятий предмета с id - " + id, e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<Lesson> getLessonsByTeacherId(int id, int count) {
        if (id == 0 || count == 0) {
            return null;
        }
        try {
            return lessonDao.getLessonsByTeacher(id, count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения занятий учителя с id - " + id, e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<Lesson> getLessonsByStudentId(int id, int count) {
        if (id == 0 || count == 0) {
            return null;
        }
        try {
            return lessonDao.getLessonsByStudent(id, count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения занятий студента с id - " + id, e);
        }
        return null;
    }

    @Override
    @Nullable
    public List<Lesson> getLessonsLast(int count) {
        if (count == 0) {
            return null;
        }
        try {
            return lessonDao.getLessons(count);
        } catch (SQLException e) {
            logger.warn("Ошибка получения последних созданных занятий", e);
        }
        return null;
    }

    @Override
    public boolean createLesson(Lesson lesson) {
        if (lesson == null) {
            return false;
        }
        try {
            return lessonDao.addLesson(lesson);
        } catch (SQLException e) {
            logger.warn("Ошибка создания занятия", e);
        }
        return false;
    }

    @Override
    public boolean deleteLessonById(int idLesson) {
        if (idLesson == 0) {
            return false;
        }
        try {
            return lessonDao.deleteLessonById(idLesson);
        } catch (SQLException e) {
            logger.warn("Ошибка удаления занятия с id - " + idLesson, e);
        }
        return false;
    }
}
