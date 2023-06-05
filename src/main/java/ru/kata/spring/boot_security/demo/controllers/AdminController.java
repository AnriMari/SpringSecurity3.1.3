package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RegistrationService;
import ru.kata.spring.boot_security.demo.services.RoleService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RegistrationService registrationService;
    private final RoleService roleService;

    @Autowired
    public AdminController(RegistrationService registrationService, RoleService roleService) {
        this.registrationService = registrationService;
        this.roleService = roleService;
    }

    @GetMapping("/adminpage")
    public String adminPage() {
        return "adminpage";
    }

    @GetMapping("/usersinfo")
    public String showUsersInfo(Model model, Model role){
        role.addAttribute("rolesList", roleService.getRolesList());
        model.addAttribute("users", registrationService.showAllUsers());
        return "usersinfo";
    }

    @GetMapping("/user/{id}")
    public String userPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", registrationService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String addUser(Model model, Model role) {
        model.addAttribute("user", new User());
        role.addAttribute("rolesList", roleService.getRolesList());
        return "new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") User user){
        return "redirect:/usersinfo";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Long id, Model role) {
        role.addAttribute("rolesList", roleService.getRolesList());
        model.addAttribute("user", registrationService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id) {
        registrationService.editUser(id, user);

        return "redirect:/usersinfo";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        registrationService.deleteUser(id);

        return "redirect:/usersinfo";
    }

}
