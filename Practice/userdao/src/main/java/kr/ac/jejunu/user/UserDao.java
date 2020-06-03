package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;
    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext=jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);

        User user = jdbcContext.jdbcContextForGet(statementStrategy);

        return user;
    }

    public void insert(User user) throws  SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);

        jdbcContext.jdbcContextForInsert(user, statementStrategy);

    }

    public void update(User user)  {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdateDelete(statementStrategy);

    }

    public void delete(Integer id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdateDelete(statementStrategy);

    }

}
