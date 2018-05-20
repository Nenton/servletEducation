package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import org.apache.log4j.Logger;
import ru.innopolis.stc9.susev.pojo.Role;

import java.util.List;

public interface IRoleService {
    Logger logger = Logger.getLogger(IRoleService.class);
    @Nullable
    List<Role> getRoles();

    @Nullable
    boolean createRole(Role role);

    @Nullable
    boolean deleteRole(int id);
}
