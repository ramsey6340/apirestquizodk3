package com.groupe3.apirestquizodk3.controllers;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.models.Result;
import com.groupe3.apirestquizodk3.services.QuestionService;
import com.groupe3.apirestquizodk3.services.QuizService;
import com.groupe3.apirestquizodk3.services.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/users/{userId}/quizzes/{quizId}/")
public class PlayQuizController {

    @Autowired
    private ResultService resultService;
    @Autowired
    private QuestionService questionService;

    @Operation(summary = "Lancement du quiz")
    @GetMapping(value = "play")
    public Optional<Question> getNextQuestion(@PathVariable Long userId, @PathVariable Long quizId) {
        return questionService.getNextQuestion(userId, quizId);
    }

    @Operation(summary = "Terminer un quiz")
    @GetMapping("end")
    public Optional<Result> endGame(@PathVariable Long userId, @PathVariable Long quizId){
        return resultService.endGame(userId, quizId);
    }

    @Operation(summary = "Repondre à un quiz")
    @GetMapping(value = "play", params = "answer")
    public Result respondQuestion(@PathVariable Long userId, @PathVariable Long quizId, @RequestParam("answer") int answer) {
        return resultService.respondQuestion(userId, quizId, answer);
    }

    @Operation(summary = "Classement d'un quiz")
    @GetMapping("rank")
    public ResponseEntity<Map<Integer, Map<String, String>>> getRank(@PathVariable Long userId, @PathVariable Long quizId) {
        Map<Integer, Map<String, String>> maxScoreResults = resultService.getMaxScoreResultsByUserAndQuiz(quizId);
        return ResponseEntity.ok(maxScoreResults);
    }

}

