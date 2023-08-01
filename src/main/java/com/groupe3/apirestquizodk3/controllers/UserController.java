package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.User;
import com.groupe3.apirestquizodk3.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    public UserService userService;
    @Operation(summary = "ceci est la methode de l'endpoint pour recuperer la liste  des utilisateurs")
    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @Operation(summary = "ceci est la methode de l'endpoint pour inserer un utilisateur")
    @PostMapping("users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @Operation(summary = "ceci est la methode de l'endpoint pour recuperer un utilisateur specifique")
    @GetMapping("users/{userId}")
    public User getSpecificUser(@PathVariable Long userId){
        return userService.getSpecificUser(userId);
    }
    @Operation(summary = "ceci est la methode de l'endpoint pour la connexion d'un utilisateur ")
    @PostMapping("users/connect")
    public Optional<User> getConnect(@RequestBody Map<String, String> connexionData){
        return userService.getConnect(connexionData);
    }

}
