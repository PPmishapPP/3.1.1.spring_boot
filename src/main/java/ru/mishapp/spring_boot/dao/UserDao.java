package ru.mishapp.spring_boot.dao;


import ru.mishapp.spring_boot.models.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUser(long id);
    User getUserByLogin(String login);
}
