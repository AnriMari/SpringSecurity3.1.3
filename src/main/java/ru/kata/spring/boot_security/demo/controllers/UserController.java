package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



//    public User currentUserName(Principal principal){
//        String username = principal.getName();
//        User user = new User();
//        if(null != username){
//            user = userService.getUserByUsername(username);
//        }
//        return user;
//}
    @GetMapping("/user")
    public User getCurrentUser(Principal principal) {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

//    @GetMapping("/user")
//    public User currentUserName(Principal principal) {
//        return (User) principal;
//    }
}

