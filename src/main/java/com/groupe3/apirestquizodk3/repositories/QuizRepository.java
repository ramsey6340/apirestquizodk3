package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // Pour rechercher un quiz par titre
    List<Quiz> findByTitle(String title);

    //Pour rechercher quiz par nb max de questions
    List<Quiz> findByNbMaxQuestion(Integer nbMaxQuestion);

    //Pour rechercher quiz par son ID
    List<Quiz> findByQuizId(Long quiz_id);

    // Pour rechercher des quiz par visibilité
    List<Quiz> findByVisibility(String visibility);

    //Pour rechercher des quiz par date de création
    List<Quiz> findByCreationDate(LocalDate creationDate);

    List<Quiz> findByUserUserId(Long userId);

    //******************************************************************************************return une quiz
    Optional<Quiz> findByUserUserIdAndQuizId(Long userId, Long quizId);
}
