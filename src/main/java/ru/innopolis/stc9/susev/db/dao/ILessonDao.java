package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface ILessonDao {
    boolean addLesson(Lesson lesson) throws SQLException;

    boolean deleteLesson(Lesson lesson) throws SQLException;

    @Nullable
    Lesson getLessonById(int id) throws SQLException;

    boolean updateLesson(Lesson lesson) throws SQLException;

    boolean deleteLessonById(int idLesson) throws SQLException;

    List<Lesson> getLessons(int count) throws SQLException;
}
