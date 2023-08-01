package com.groupe3.apirestquizodk3.services;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.models.User;
import com.groupe3.apirestquizodk3.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Data
public class UserService {
    @Autowired
    public UserRepository userRepository;
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public  User addUser(User user){
        return userRepository.save(user);
    }
     public User  getSpecificUser(Long userId){
        return userRepository.findByUserId(userId);
     }
     public Optional<User> getConnect (Map<String, String> connexionData){
        String login = connexionData.get("login");
        String password = connexionData.get("password");
        return userRepository.findByLoginAndPassword(login, password);

     }

}
