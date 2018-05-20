package ru.innopolis.stc9.susev.services;

import com.sun.istack.internal.Nullable;
import org.apache.log4j.Logger;
import ru.innopolis.stc9.susev.pojo.Role;

public interface IAuthService {
    Logger logger = Logger.getLogger(IAuthService.class);
    boolean checkAuth(String login, String password);
    @Nullable
    Role getRoleByUserLogin(String login);
}
