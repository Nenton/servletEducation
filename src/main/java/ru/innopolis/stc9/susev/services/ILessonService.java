package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import org.apache.log4j.Logger;
import ru.innopolis.stc9.susev.pojo.Lesson;
import ru.innopolis.stc9.susev.pojo.Subject;
import ru.innopolis.stc9.susev.pojo.User;

import java.util.List;

public interface ILessonService {
    Logger logger = Logger.getLogger(ILessonService.class);

    /**
     * Get user by login
     */
    @Nullable
    User getUserByLogin(String login);

    /**
     * Get all subjects
     */
    @Nullable
    List<Subject> getSubjects();

    /**
     * Get users with role student
     */
    @Nullable
    List<User> getStudents();

    /**
     * Get users with role teacher
     */
    @Nullable
    List<User> getTeachers();

    /**
     * Get lessons by subject id. Count is size list
     */
    @Nullable
    List<Lesson> getLessonsBySubject(int id, int count);

    /**
     * Get lessons by teacher id. Count is size list
     */
    @Nullable
    List<Lesson> getLessonsByTeacherId(int id, int count);

    /**
     * Get lessons by student id. Count is size list
     */
    @Nullable
    List<Lesson> getLessonsByStudentId(int id, int count);

    /**
     * Get last created lessons. Count is size list
     */
    @Nullable
    List<Lesson> getLessonsLast(int count);

    /**
     * Create lesson entity
     */
    boolean createLesson(Lesson lesson);

    /**
     * Delete lesson entity
     */
    boolean deleteLessonById(int idLesson);
}
