package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Subject;

public interface ISubjectDao {
    boolean addSubject(Subject manufacturer);

    boolean deleteSubject(Subject manufacturer);

    @Nullable
    Subject getSubjectById(int id);

    boolean updateSubject(Subject manufacturer);
}
