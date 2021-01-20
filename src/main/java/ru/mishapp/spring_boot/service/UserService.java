package ru.mishapp.spring_boot.service;

import ru.mishapp.spring_boot.models.Role;
import ru.mishapp.spring_boot.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void deleteUser(long id);
    User getUser(long id);
    User getUserByLogin(String login);
    List<User> getAllUsers();
    Role getRole(long id);
    List<Role> getAllRoles();
}
