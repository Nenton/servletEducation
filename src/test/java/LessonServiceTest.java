import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.innopolis.stc9.susev.db.dao.UserDao;
import ru.innopolis.stc9.susev.pojo.Role;
import ru.innopolis.stc9.susev.pojo.User;
import ru.innopolis.stc9.susev.services.LessonService;

import java.sql.SQLException;

public class LessonServiceTest {
    private LessonService lessonService;

    @Before
    public void before() {
        lessonService = new LessonService();
    }

    @Test
    public void getUserByLoginTest() {
        UserDao mock = Mockito.mock(UserDao.class);
        int id = 13;
        String login = "nenton";
        String pass = "nentonPass";
        Role role = new Role(1, "admin");
        String fullName = "Сергей";
        try {
            User user = new User(id, login, pass, role, fullName);
            Mockito.when(mock.getUserByLogin(login)).thenReturn(user);
        } catch (SQLException e) {
            Assert.fail();
        }
        lessonService.setUserDao(mock);

        User nenton = lessonService.getUserByLogin(login);
        boolean condition = nenton.getId() == 13 &&
                nenton.getRole().equals(new Role(1, "admin")) &&
                nenton.getPasswordHash().equals("nentonPass") &&
                nenton.getFullName().equals("Сергей") &&
                nenton.getLogin().equals("nenton");

        Assert.assertTrue(condition);
    }

    @Test
    public void getUserByLoginTestReturnNull() {
        String login = "";
        UserDao mock = Mockito.mock(UserDao.class);
        User user = Mockito.mock(User.class);
        try {
            Mockito.when(mock.getUserByLogin(login)).thenReturn(user);
            lessonService.setUserDao(mock);
        } catch (SQLException e) {
            Assert.fail();
        }
        User user1 = lessonService.getUserByLogin(null);
        User user2 = lessonService.getUserByLogin(login);
        Assert.assertNull(user1);
        Assert.assertNull(user2);
    }
}
