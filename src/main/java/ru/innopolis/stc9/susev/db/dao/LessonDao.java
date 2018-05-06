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
        statement.setInt(4, lesson.getMarkId());
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
                    set.getInt("id"),
                    set.getInt("subject"),
                    set.getInt("student"),
                    set.getInt("teacher"),
                    set.getInt("mark"),
                    set.getBoolean("attendance")
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
        statement.setInt(4, lesson.getMarkId());
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
                "  lesson.*,\n" +
                "  s2.subject_name,\n" +
                "  s3.student_name,\n" +
                "  s3.course,\n" +
                "  t2.teacher_name,\n" +
                "  m2.value,\n" +
                "  m2.system\n" +
                "\n" +
                "from lesson\n" +
                "  inner join subjects s2 on lesson.subject = s2.id\n" +
                "  inner join students s3 on lesson.student = s3.id\n" +
                "  inner join teachers t2 on lesson.teacher = t2.id\n" +
                "  inner join marks m2 on lesson.mark = m2.id\n" +
                "where lesson.student = ?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Subject subject = new Subject(resultSet.getInt("subject"),
                    resultSet.getString("subject_name"));
            Student student = new Student(resultSet.getInt(resultSet.getInt("student")),
                    resultSet.getString("student_name"),
                    resultSet.getInt("course"));
            Teacher teacher = new Teacher(resultSet.getInt("teacher"),
                    resultSet.getString("teacher_name"));
            Mark mark = new Mark(resultSet.getInt("mark"),
                    resultSet.getInt("value"),
                    resultSet.getString("system"));
            lessons.add(new Lesson(resultSet.getInt("id"),
                    subject, subject.getId(),
                    student, student.getId(),
                    teacher, teacher.getId(),
                    mark, mark.getId(),
                    resultSet.getBoolean("attendance")
            ));
        }

        return lessons;
    }
}
