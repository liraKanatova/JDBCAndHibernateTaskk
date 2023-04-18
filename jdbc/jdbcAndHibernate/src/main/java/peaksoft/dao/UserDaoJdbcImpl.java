package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJdbcImpl implements UserDao {
    private final Connection connection;

    public UserDaoJdbcImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sql = "create table if not exists users(id serial primary key," +
                "name varchar," +
                "last_name varchar," +
                "age smallint not null);";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Successfully created..");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully drop table !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name,last_name,age)values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(Long id) {
        try {
            String sql = " delete from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement.executeUpdate());
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from users;";
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "delete from users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Successfully cleaned !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}