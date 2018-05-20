package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface ILessonDao {
    String COLUMN_ID = "id";
    String COLUMN_SUBJECT = "subject";
    String COLUMN_TEACHER = "teacher";
    String COLUMN_STUDENT = "student";
    String COLUMN_MARK = "mark";
    String COLUMN_ATTENDANCE = "attendance";

    /**
     * Insert lesson into DB
     */
    boolean addLesson(Lesson lesson) throws SQLException;

    /**
     * Delete lesson from DB
     */
    boolean deleteLesson(Lesson lesson) throws SQLException;

    /**
     * Get lesson from DB by lesson id
     */
    @Nullable
    Lesson getLessonById(int id) throws SQLException;

    /**
     * Update lesson from DB
     */
    boolean updateLesson(Lesson lesson) throws SQLException;

    /**
     * Delete lesson from DB by lesson id
     */
    boolean deleteLessonById(int idLesson) throws SQLException;

    /**
     * Get last lesson from DB
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessons(int count) throws SQLException;

    /**
     * Get lessons from DB by subjectId
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessonsBySubject(int id, int count) throws SQLException;

    /**
     * Get lessons from DB by teacher id
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessonsByTeacher(int id, int count) throws SQLException;

    /**
     * Get lessons from DB by student id
     *
     * @param count Count lessons in array
     */
    List<Lesson> getLessonsByStudent(int id, int count) throws SQLException;
}
