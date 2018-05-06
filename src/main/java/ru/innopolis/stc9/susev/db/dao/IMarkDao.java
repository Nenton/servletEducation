package ru.innopolis.stc9.susev.db.dao;

import com.sun.istack.internal.Nullable;
import ru.innopolis.stc9.susev.pojo.Mark;

public interface IMarkDao {
    boolean addMark(Mark mark);

    boolean deleteMark(Mark mark);

    @Nullable
    Mark getMarkById(int id);

    boolean updateMark(Mark mark);
}
