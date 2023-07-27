package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.User;
import com.groupe3.apirestquizodk3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    public UserService userService;
    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping("users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("users/{userId}")
    public User getSpecificUser(@PathVariable Long userId){
        return userService.getSpecificUser(userId);
    }
    @PostMapping("users/connect")
    public  User getConnect(@RequestBody Map<String, String> connexionData){
        return userService.getConnect(connexionData);
    }

}
