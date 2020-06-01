package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component   //Component가 존재하면 sping이 알아서 bean을 찾아서 DI(Dependency injection) 를 등록해준다 단 생성자가 존재해야한다.
public class UserDao {

    @Autowired    //Autowired가 존재하면 생성자가 없어도 bean을 찾아서 알아서 DI를 해준다.
    private JdbcTemplate jdbcTemplate;


//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public User get(Integer id)  {
        Object[] params = new Object[] {id};
        String sql ="select id, name, password from userinfo where id = ?";

        return jdbcTemplate.query(sql, params, rs -> {
            User user = null;
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        });
    }

    public void insert(User user)  {
        Object[] params = new Object[] {user.getName(), user.getPassword()};
        String sql ="insert into userinfo(name,password) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) {
            String sql = "update userinfo set name =? ,password =? where id=?";
            Object[] params = new Object[] {user.getName(), user.getPassword(), user.getId()};

        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
            String sql = "delete from userinfo where id=?";
            Object[] params = new Object[] {id};

        jdbcTemplate.update(sql,params);
    }

}