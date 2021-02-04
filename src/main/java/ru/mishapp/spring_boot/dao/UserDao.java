package ru.mishapp.spring_boot.dao;


import ru.mishapp.spring_boot.models.User;

import java.util.List;

public interface UserDao {
    User saveUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUser(Long id);
    User getUserByLogin(String login);
}
