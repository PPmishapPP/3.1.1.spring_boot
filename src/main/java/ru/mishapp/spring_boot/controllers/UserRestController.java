package ru.mishapp.spring_boot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mishapp.spring_boot.models.User;
import ru.mishapp.spring_boot.service.UserService;

import java.security.Principal;

@RestController
public class UserRestController {

    UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/api")
    public ResponseEntity<User> getUser(Principal principal){
        User user = userService.getUserByLogin(principal.getName());
        return user == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(user, HttpStatus.OK);
    }
}
