package ru.mishapp.spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mishapp.spring_boot.dao.UserDao;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    private final Map<String, String> googleUsers = new HashMap<>();

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (googleUsers.containsKey(s)){
            return User.builder()
                    .username(s)
                    .password(googleUsers.get(s))
                    .authorities("USER")
                    .build();
        }
        return userDao.getUserByLogin(s);
    }

    public void addGoogleUser(String name, String password){
        googleUsers.put(name, password);
    }
}
