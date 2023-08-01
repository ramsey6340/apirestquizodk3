package com.groupe3.apirestquizodk3.services;
import com.groupe3.apirestquizodk3.models.Result;
import com.groupe3.apirestquizodk3.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    public ResultRepository resultRepository;
    public List<Result>  getUserResultsForSpecificQuiz (Long userId, Long quizId){
        return resultRepository.findByUserUserIdAndQuizQuizId(userId, quizId);
    }

    public Result getSpecificResultForSpecificUserForSpecificQuiz(Long userId, Long quizId, Long resultId) {
        return resultRepository.findByResultIdAndUserUserIdAndQuizQuizId(userId, quizId, resultId);
    }

    public List<Result> getResultsForSpecificQuiz(Long quizId) {
        return resultRepository.findByQuizQuizId(quizId);
    }

    public Result getSpecificResultForSpecificQuiz(Long quizId, Long resultId) {

        return resultRepository.findByResultIdAndQuizQuizId(quizId, resultId);
    }

    public List<Result> getResultsForSpecificQuizAndSpecificScore(Long quizId, Integer score) {
        return resultRepository.findByQuizQuizIdAndScore(quizId, score);
    }

    public List<Result> getResultsRanksForQuiz(Long quizId) {
        return resultRepository.findByQuizQuizId(quizId);
    }
}
