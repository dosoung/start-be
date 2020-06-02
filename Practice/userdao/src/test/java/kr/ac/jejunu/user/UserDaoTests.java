package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    Integer id = 1;
    String name = "도현";
    String password = "1234";

    @Test
    public void get() throws SQLException, ClassNotFoundException {


        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {

        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        assertThat(user.getId(),greaterThan(0));

        User insertedUser = userDao.get(user.getId());

        assertThat(insertedUser.getName(),is(name));
        assertThat(insertedUser.getPassword(),is(password));



    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {


        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insertHalla() throws SQLException, ClassNotFoundException {

        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        assertThat(user.getId(),greaterThan(0));

        User insertedUser = userDao.get(user.getId());

        assertThat(insertedUser.getName(),is(name));
        assertThat(insertedUser.getPassword(),is(password));



    }

}
