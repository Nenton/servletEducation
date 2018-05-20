package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.db.connection.ConnectionManager;
import ru.innopolis.stc9.susev.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.susev.pojo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao class for Lesson entity
 */
public class LessonDao implements ILessonDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_TEACHER = "teacher";
    private static final String COLUMN_STUDENT = "student";
    private static final String COLUMN_MARK = "mark";
    private static final String COLUMN_ATTENDANCE = "attendance";
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    /**
     * Insert lesson into DB
     */
    @Override
    public boolean addLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("insert into lesson(subject, student, teacher, mark, attendance)" +
                    " values (?, ?, ?, ?, ?)");
            setParamsIntoStatement(statement, lesson);
            return statement.execute();
        }
    }

    /**
     * Delete lesson from DB
     */
    @Override
    public boolean deleteLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        return deleteLessonById(lesson.getId());
    }

    /**
     * Get lesson from DB by lesson id
     */
    @Override
    public Lesson getLessonById(int id) throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("select * from lesson where id = ?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return getLessonFromDb(set);
            }
            return null;
        }
    }

    /**
     * Update lesson from DB
     */
    @Override
    public boolean updateLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("update lesson " +
                    "set subject = ?, student = ?, teacher = ?, mark = ?, attendance = ? " +
                    "where id = ?");
            setParamsIntoStatement(statement, lesson);
            statement.setInt(6, lesson.getId());
            statement.executeUpdate();
            return true;
        }
    }

    /**
     * Get lessons from DB by subjectId
     */
    @Nullable
    public List<Lesson> getLessonsBySubject(int idSubject) throws SQLException {
        if (idSubject == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("select lesson.* from lesson\n" +
                    "where lesson.subject = ? order by lesson.id desc limit ?");
            statement.setInt(1, idSubject);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }

    /**
     * Delete lesson from DB by lesson id
     */
    @Override
    public boolean deleteLessonById(int idLesson) throws SQLException {
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("delete from lesson where id = ?");
            statement.setInt(1, idLesson);
            return statement.execute();
        }
    }

    /**
     * Get lessons from DB by teacher id
     * * @param count Count lessons in array
     */
    @Nullable
    public List<Lesson> getLessonsByTeacher(int id, int count) throws SQLException {
        if (id == 0 || count == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("select lesson.* from lesson\n" +
                    "where lesson.teacher = ? order by lesson.id desc limit ?");
            statement.setInt(1, id);
            statement.setInt(2, count);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }

    /**
     * Get lessons from DB by student id
     * * @param count Count lessons in array
     */
    @Nullable
    public List<Lesson> getLessonsByStudent(int id, int count) throws SQLException {
        if (id == 0 || count == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("select lesson.*\n" +
                    "from lesson where lesson.student = ? order by lesson.id desc limit ?");
            statement.setInt(1, id);
            statement.setInt(2, count);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }

    /**
     * Get last lesson from DB
     * @param count Count lessons in array
     */
    @Override
    public List<Lesson> getLessons(int count) throws SQLException {
        if (count == 0) {
            return null;
        }
        try (Connection connection = conManager.getConnection()) {
            PreparedStatement statement = null;
            statement = connection.prepareStatement("select * from lesson order by lesson.id desc limit ?;");
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }

    /**
     * Parse results from ResultSer into List
     */
    private List<Lesson> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        while (resultSet.next()) {
            int idLesson = resultSet.getInt(COLUMN_ID);
            int idStudent = resultSet.getInt(COLUMN_STUDENT);
            int idTeacher = resultSet.getInt(COLUMN_TEACHER);
            int idSubject = resultSet.getInt(COLUMN_SUBJECT);
            int mark = resultSet.getInt(COLUMN_MARK);
            boolean attendance = resultSet.getBoolean(COLUMN_ATTENDANCE);

            UserDao userDao = new UserDao();
            User student = userDao.getUserById(String.valueOf(idStudent));
            User teacher = userDao.getUserById(String.valueOf(idTeacher));
            Subject subject = new SubjectDao().getSubjectById(idSubject);

            lessons.add(new Lesson(idLesson,
                    subject, subject.getId(),
                    student, student.getId(),
                    teacher, teacher.getId(),
                    mark,
                    attendance));
        }
        return lessons;
    }

    /**
     * Mapping statement from lesson entity
     */
    private void setParamsIntoStatement(PreparedStatement statement, Lesson lesson) throws SQLException {
        statement.setInt(1, lesson.getSubjectId());
        statement.setInt(2, lesson.getStudentId());
        statement.setInt(3, lesson.getTeacherId());
        statement.setInt(4, lesson.getMark());
        statement.setBoolean(5, lesson.isAttendance());
    }

    /**
     * Parse results from ResultSet into Lesson
     */
    private Lesson getLessonFromDb(ResultSet set) throws SQLException {
        return new Lesson(
                set.getInt(COLUMN_ID),
                set.getInt(COLUMN_SUBJECT),
                set.getInt(COLUMN_STUDENT),
                set.getInt(COLUMN_TEACHER),
                set.getInt(COLUMN_MARK),
                set.getBoolean(COLUMN_ATTENDANCE)
        );
    }
}
