package com.zenika.academy.barbajavas.wordle.domain.controller;

import com.zenika.academy.barbajavas.wordle.application.UserManager;
import com.zenika.academy.barbajavas.wordle.domain.model.User;
import com.zenika.academy.barbajavas.wordle.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user){
        return userManager.createUser(user.getName(), user.getEmail());
    }

    @GetMapping("/users")
    public Map<String, User> getUsers(){
        return userManager.getAllUsers();
    }

    @GetMapping("/users/{userTid}")
    public Optional<User>getUser(@PathVariable(value="userTid")String userTid){
        return userManager.getUser(userTid);
    }

    @GetMapping("/users/name")
    public Collection<User>getUserByName(@RequestParam(value="name",defaultValue = "name")String name){
        return userManager.getUserByName(name);
    }
}
