package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserUserIdAndQuizQuizId(Long userId, Long quizId);

    Result findByResultIdAndUserUserIdAndQuizQuizId(Long userId, Long quizId, Long resultId);

    List<Result> findByQuizQuizId(Long quizId);
    Optional<Result> findByUserUserIdAndQuizQuizIdAndStateFalse(Long userId, Long quizId);
    Optional<Result> findByResultIdAndQuizQuizId(Long quizId, Long resultId);

    List<Result> findByQuizQuizIdAndScore(Long quizId, Integer score);
    // Obtenir la liste des resultats, dont le score est le plus grand, pour chaque utilisateur qui a participer à un quiz donné
    @Query("SELECT r FROM Result r WHERE r.quiz.quizId = :quizId AND r.score = (SELECT MAX(r2.score) FROM Result r2 WHERE r2.user.userId = r.user.userId AND r2.quiz.quizId = :quizId) ORDER BY r.score DESC")
    List<Result> findMaxScoreResultsByUserAndQuiz(@Param("quizId") Long quizId);
}
