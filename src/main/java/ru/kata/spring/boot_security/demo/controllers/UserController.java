package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.security.Principal;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("user", principal.getName());
        return "user";
    }
}
