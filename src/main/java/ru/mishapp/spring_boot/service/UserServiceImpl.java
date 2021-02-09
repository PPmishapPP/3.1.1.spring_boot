package ru.mishapp.spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mishapp.spring_boot.dao.RoleDao;
import ru.mishapp.spring_boot.dao.UserDao;
import ru.mishapp.spring_boot.models.Role;
import ru.mishapp.spring_boot.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public User saveUser(User user) {
        String bCryptPassword = user.getPassword().isEmpty() ?
                getUser(user.getId()).getPassword() :
                passwordEncoder.encode(user.getPassword());
        user.setPassword(bCryptPassword);
        return userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }


    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
