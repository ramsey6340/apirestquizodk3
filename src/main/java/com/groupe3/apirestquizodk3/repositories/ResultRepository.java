package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserUserIdAndQuizQuizId(Long userId, Long quizId);

    Result findByResultIdAndUserUserIdAndQuizQuizId(Long userId, Long quizId, Long resultId);

    List<Result> findByQuizQuizId(Long quizId);

    Result findByResultIdAndQuizQuizId(Long quizId, Long resultId);

    List<Result> findByQuizQuizIdAndScore(Long quizId, Integer score);
}
