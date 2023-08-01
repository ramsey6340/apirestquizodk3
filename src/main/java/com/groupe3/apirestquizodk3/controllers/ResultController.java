package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.Result;
import com.groupe3.apirestquizodk3.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ResultController {
    @Autowired
    public ResultService resultService;
    @GetMapping("users/{userId}/quizzes/{quizId}/results")
    public List<Result> getUserResultsForSpecificQuiz(@PathVariable Long userId, @PathVariable Long quizId){
        return resultService.getUserResultsForSpecificQuiz(userId, quizId);
    }
    @GetMapping("users/{userId}/quizzes/{quizId}/results/{resultId}")
    public Result getSpecificResultForSpecificUserForSpecificQuiz(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long resultId){
        return resultService.getSpecificResultForSpecificUserForSpecificQuiz(userId, quizId, resultId);
    }
    @GetMapping("quizzes/{quizId}/results")
    public  List<Result> getResultsForSpecificQuiz(@PathVariable Long quizId){
        return resultService.getResultsForSpecificQuiz(quizId);
    }
    @GetMapping("quizzes/{quizId}/results/{resultId}")
    public Result getSpecificResultForSpecificQuiz(@PathVariable Long quizId, @PathVariable Long resultId){
        return resultService.getSpecificResultForSpecificQuiz(quizId, resultId);
    }
    @GetMapping("quizzes/{quizId}/results?score={x}")
    public List<Result> getResultsForSpecificQuizAndSpecificScore(@PathVariable Long quizId, @PathVariable Integer score){
        return resultService.getResultsForSpecificQuizAndSpecificScore(quizId, score);
    }
    @GetMapping("quizzes/{quizId}/ranking")
    public List<Result> getResultsRanksForQuiz(@PathVariable Long quizId){
        return resultService.getResultsRanksForQuiz(quizId);
    }
}
