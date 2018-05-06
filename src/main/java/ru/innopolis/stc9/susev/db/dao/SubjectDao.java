package ru.innopolis.stc9.susev.db.dao;


import ru.innopolis.stc9.susev.db.connection.ConnectionManager;
import ru.innopolis.stc9.susev.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.susev.pojo.Subject;

public class SubjectDao implements ISubjectDao {
    private ConnectionManager manager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addSubject(Subject manufacturer) {
        return false;
    }

    @Override
    public boolean deleteSubject(Subject manufacturer) {
        return false;
    }

    @Override
    public Subject getSubjectById(int id) {
        return null;
    }

    @Override
    public boolean updateSubject(Subject manufacturer) {
        return false;
    }
}
