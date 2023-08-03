package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // Pour rechercher un quiz par titre
    List<Quiz> findAllByTitleContaining(String keyword);

    //Pour rechercher quiz par nb max de questions
    List<Quiz> findByNbMaxQuestion(Integer nbMaxQuestion);

    //Pour rechercher quiz par son ID
   Optional<Quiz> findByQuizId(Long quiz_id);

    // Pour rechercher des quiz par visibilité
    List<Quiz> findByVisibility(String visibility);

    //Pour rechercher des quiz par date de création
    List<Quiz> findByCreationDate(LocalDate creationDate);

    List<Quiz> findAllByUserUserId(Long userId);

    //******************************************************************************************return une quiz
    Optional<Quiz> findByUserUserIdAndQuizId(Long userId, Long quizId);
}
