package com.zenika.academy.barbajavas.wordle.domain.repository;

import com.zenika.academy.barbajavas.wordle.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {
    private Map<String,User> users= new HashMap<>();

    public void saveUser(User user){
        this.users.put(user.getUserTid(),user);
    }

    public Optional<User>findUserTid(String userTid){
        return Optional.ofNullable(users.get(userTid));
    }
    public Collection<User> findUserName(String userName){
        Collection<User>userList=users.values();
        userList.removeIf(user -> !Objects.equals(user.getName(), userName));
        System.out.println(userList);
        return userList;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
