package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
    List<User> findAll();
    User findByLoginAndPassword(String login, String password);


}
