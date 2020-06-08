package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    Integer id = 1;
    String password = "1234";
    String name = "도현";
    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao",UserDao.class);
        }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        User user = userDao.getId(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));

    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        assertThat(user.getId() ,greaterThan(0));

        User insertedUser = userDao.getId(user.getId());
        assertThat(insertedUser.getName(),is(name));
        assertThat(insertedUser.getPassword(),is(password));

    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        String updatedName = "SOUNG";
        String updatedPassword="0987";
        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);
        User updatedUser = userDao.getId(user.getId());
        assertThat(updatedUser.getName(),is(updatedName));
        assertThat(updatedUser.getPassword(),is(updatedPassword));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.getId(user.getId());
        assertThat(deletedUser, IsNull.nullValue());

    }
}
