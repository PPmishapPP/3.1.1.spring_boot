package ru.mishapp.spring_boot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mishapp.spring_boot.models.User;
import ru.mishapp.spring_boot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String adminPage(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        model.addAttribute("user", new User());
        model.addAttribute("all_roles", userService.getAllRoles());
        return "admin";
    }


    @PostMapping("/del")
    public String delUser(@RequestParam("user_id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam("role_select") Long[] roleIds,
                           @RequestParam("password") String password) {
        for (Long id : roleIds) {
            user.addRole(userService.getRole(id));
        }

        String bCryptPassword = password.isEmpty() ?
                userService.getUser(user.getId()).getPassword() :
                passwordEncoder.encode(password);

        user.setPassword(bCryptPassword);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
