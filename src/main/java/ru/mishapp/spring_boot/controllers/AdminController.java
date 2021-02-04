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

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("all_roles", userService.getAllRoles());
        return "admin";
    }


    @PostMapping("/del")
    public String delUser(@RequestParam("user_id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
