package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByQuizQuizId(Long quizId);
    Optional<Question> findById(Long questionId);
    List<Question> findByQuizQuizIdAndQuizUserUserId(Long quizId, Long userId);
    Question findByquestionIdAndQuizQuizIdAndQuizUserUserId(Long questionId, Long quizId, Long userId);
}
