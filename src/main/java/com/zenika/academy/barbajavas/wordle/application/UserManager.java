package com.zenika.academy.barbajavas.wordle.application;

import com.zenika.academy.barbajavas.wordle.domain.model.User;
import com.zenika.academy.barbajavas.wordle.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserManager {
    private UserRepository userRepository;
    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email){
        User user= new User(UUID.randomUUID().toString(),name, email);
        userRepository.saveUser(user);
        return user;
    }

    public Optional<User> getUser(String userTid){
        return userRepository.findUserTid(userTid);
    }

    public Collection<User> getUserByName(String userName){
        return userRepository.findUserName(userName);
    }

    public Map<String, User> getAllUsers() {
        return userRepository.getUsers();
    }
}
