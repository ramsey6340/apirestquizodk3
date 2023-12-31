package com.groupe3.apirestquizodk3.services;
import com.groupe3.apirestquizodk3.models.User;
import com.groupe3.apirestquizodk3.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User addUser(User user){
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }
    public Optional<User> getUserByLoginAndPassword(String login, String password){
        return userRepository.findByLoginAndPassword(login, password);
    }
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> userRepository.delete(value));
    }
    public Optional<User> updateWithPutValueUser(Long userId, User newUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User existingUser = userOptional.get();

            existingUser.setFirstName(newUser.getFirstName());
            existingUser.setLastName(newUser.getLastName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setLogin(newUser.getLogin());
            existingUser.setPassword(newUser.getPassword());

            User upadateUser = userRepository.save(existingUser);
            return Optional.of(upadateUser);
        }
        return Optional.empty();
    }
    public ResponseEntity<Optional<User>> updateWithPathValueUser(Long userId, Map<String, String> updateUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Mise à jour des données de l'utilisateur
        if(updateUser.containsKey("firstName")){
            user.get().setFirstName(updateUser.get("firstName"));
        }
        if(updateUser.containsKey("lastName")){
            user.get().setLastName(updateUser.get("lastName"));
        }
        if(updateUser.containsKey("email")){
            user.get().setEmail(updateUser.get("email"));
        }
        if(updateUser.containsKey("login")){
            user.get().setLogin(updateUser.get("login"));
        }
        if(updateUser.containsKey("password")){
            user.get().setPassword(updateUser.get("password"));
        }

        userRepository.save(user.get());
        return ResponseEntity.ok(user);
    }
}