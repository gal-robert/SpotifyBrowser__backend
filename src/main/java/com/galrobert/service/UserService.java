package com.galrobert.service;


import com.galrobert.domain.User;
import com.galrobert.persistance.UserRepository;
import com.galrobert.transfer.CreateUserRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void createUser(CreateUserRequest request) throws IOException, SQLException {
        System.out.println("Creating user " + request);
        userRepository.createUser(request);
    }

    public List<User> getUsers() throws IOException, SQLException {
        return userRepository.getUsers();
    }

    public User getUser(long id) throws IOException, SQLException {
        return userRepository.getUser(id);
    }
}
