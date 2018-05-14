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

public class LessonDao implements ILessonDao {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_TEACHER = "teacher";
    public static final String COLUMN_STUDENT = "student";
    public static final String COLUMN_MARK = "mark";
    public static final String COLUMN_ATTENDANCE = "attendance";
    private ConnectionManager conManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("insert into lesson(subject, student, teacher, mark, attendance)" +
                " values (?, ?, ?, ?, ?)");
        statement.setInt(1, lesson.getSubjectId());
        statement.setInt(2, lesson.getStudentId());
        statement.setInt(3, lesson.getTeacherId());
        statement.setInt(4, lesson.getMark());
        statement.setBoolean(5, lesson.isAttendance());

        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public boolean deleteLesson(Lesson lesson) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("delete from lesson where id = ?");
        statement.setInt(1, lesson.getId());
        boolean execute = statement.execute();
        connection.close();
        return execute;
    }

    @Override
    public Lesson getLessonById(int id) throws SQLException {
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("select * from lesson where id = ?");
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Lesson(
                    set.getInt(COLUMN_ID),
                    set.getInt(COLUMN_SUBJECT),
                    set.getInt(COLUMN_STUDENT),
                    set.getInt(COLUMN_TEACHER),
                    set.getInt(COLUMN_MARK),
                    set.getBoolean(COLUMN_ATTENDANCE)
            );
        }

        connection.close();
        return null;
    }

    @Override
    public boolean updateLesson(Lesson lesson) throws SQLException {
        if (lesson == null) {
            return false;
        }
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("update lesson " +
                "set subject = ?, student = ?, teacher = ?, mark = ?, attendance = ? " +
                "where id = ?");
        statement.setInt(1, lesson.getSubjectId());
        statement.setInt(2, lesson.getStudentId());
        statement.setInt(3, lesson.getTeacherId());
        statement.setInt(4, lesson.getMark());
        statement.setBoolean(5, lesson.isAttendance());
        statement.setInt(6, lesson.getId());

        statement.executeUpdate();
        connection.close();
        return true;
    }

    @Nullable
    public List<Lesson> getLessonsBySubject(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        List<Lesson> lessons = new ArrayList<>();
        return lessons;
    }

    @Nullable
    public List<Lesson> getLessonsByTeacher(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        List<Lesson> lessons = new ArrayList<>();
        return lessons;
    }

    @Nullable
    public List<Lesson> getLessonsByStudent(int id) throws SQLException {
        if (id == 0) {
            return null;
        }
        List<Lesson> lessons = new ArrayList<>();
        Connection connection;
        connection = conManager.getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement("select\n" +
                "  lesson.*\n" +
                "from lesson\n" +
                "  inner join subjects s2 on lesson.subject = s2.id\n" +
                "  inner join users s3 on lesson.student = s3.id\n" +
                "  inner join users t2 on lesson.teacher = t2.id\n" +
                "where lesson.student = ?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int idLesson = resultSet.getInt(COLUMN_ID);
            int idStudent = resultSet.getInt(COLUMN_STUDENT);
            int idTeacher = resultSet.getInt(COLUMN_TEACHER);
            int idSubject = resultSet.getInt(COLUMN_SUBJECT);
            int mark = resultSet.getInt(COLUMN_MARK);
            boolean attendance = resultSet.getBoolean(COLUMN_ATTENDANCE);

            UserDao userDao = new UserDao();
            User student = userDao.getUserById(idStudent);
            User teacher = userDao.getUserById(idTeacher);
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
}
