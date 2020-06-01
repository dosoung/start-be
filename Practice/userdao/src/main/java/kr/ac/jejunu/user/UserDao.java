package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User getid(Integer id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select id,name,password from userinfo where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;

    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name,password) value(?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());

        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {

        return connectionMaker.getConnection();
    }
}