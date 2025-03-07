package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    Object getUserById(long id);

    void addUser(User user);

    void removeUser(long id);

    void updateUser(User user);
}
