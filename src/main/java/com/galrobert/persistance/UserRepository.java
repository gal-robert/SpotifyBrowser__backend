package com.galrobert.persistance;

import com.galrobert.Util.PrintResultSet;
import com.galrobert.domain.User;
import com.galrobert.transfer.CreateUserRequest;
import com.galrobert.transfer.UpdateUserRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void createUser(CreateUserRequest request) throws IOException, SQLException {
        String sql = "INSERT INTO users (username, email, password) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getUsername());
            preparedStatement.setString(2, request.getEmail());
            preparedStatement.setString(3, request.getPassword());

            preparedStatement.executeUpdate();
        }
    }

    public void updateUser(long id, UpdateUserRequest request) throws SQLException, IOException {
        String sql = "UPDATE users SET password = ? WHERE id = ?";

        try(Connection connection = new DatabaseConfig().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getPassword());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(long id) throws SQLException, IOException {
        String sql = "DELETE FROM users WHERE id = ?";

        try(Connection connection = new DatabaseConfig().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public List<User> getUsers() throws SQLException, IOException {

        String sql = "SELECT id, email, username FROM users";

        try(Connection connection = new DatabaseConfig().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));


                users.add(user);
            }

            return users;

        }
    }

    public User getUser(long id) throws SQLException, IOException {

        String sql = "SELECT id, email, username FROM users WHERE id = " + id;

        try(Connection connection = new DatabaseConfig().getConnection();
            Statement preparedStatement = connection.createStatement();
            ResultSet result = preparedStatement.executeQuery(sql)) {

            User user = new User();
            System.out.println(user);

            result.first();
            user.setId(result.getLong("id"));
            user.setEmail(result.getString("email"));
            user.setUsername(result.getString("username"));

            return user;
        }


    }
}
