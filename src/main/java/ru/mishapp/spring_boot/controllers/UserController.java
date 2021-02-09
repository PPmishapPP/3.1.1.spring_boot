package ru.mishapp.spring_boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String informPage() {
        return "user";
    }

}
