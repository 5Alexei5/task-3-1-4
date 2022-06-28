package com.tereshin.pp314.controllers;

import com.tereshin.pp314.models.Role;
import com.tereshin.pp314.models.User;
import com.tereshin.pp314.services.RoleService;
import com.tereshin.pp314.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {

        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> allUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id) {

        return userService.getUser(id);
    }

    @GetMapping("/roles")
    public Iterable<Role> getRoles() {
        return roleService.getRoles();
    }


    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        List<Role> list = user.getRoles();
        user.setRoles(roleService.getListRoles(list.stream().map(role -> role.getAuthority()).toArray(String[]::new)));
        userService.addUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users")
    public User upDate(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roleService.getListRoles(user.getRoles().stream().map(role -> role.getAuthority()).toArray(String[]::new)));
        userService.update(user, user.getId());
        return user;
    }
}
