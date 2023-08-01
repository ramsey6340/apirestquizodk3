package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("quiz/{quizId}/questions")
    public List<Question>  getQuestionsByQuizId(@PathVariable Long quizId){
        return questionService.getQuestionsByQuizId(quizId);

    }
    @GetMapping("users/{userId}/quizzes/{quizId}/questions")
    public List<Question> getQuestionsByUserIdAndQuizId(@PathVariable Long userId, @PathVariable Long quizId){
        return questionService.getQuestionsByUserIdAndQuizId(userId, quizId);
    }
    @Operation(summary = "Création d'une question pour un quiz d'un utilisateur")
    @PostMapping("users/{userId}/quizzes/{quizId}/questions") // une question doit obligatoire appartenir à un quiz qui lui appartient à un utilisateur
    public Question addQuestion(@RequestBody Question question, @PathVariable Long userId, @PathVariable Long quizId){
        return questionService.addQuestion(question, userId, quizId);
    }
    @GetMapping("users/{userId}/quizzes/{quizId}/questions/{questionId}")
    public Question getQuestionByIdAndUserIdAndQuizId(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId){
        return questionService.getQuestionByIdAndUserIdAndQuizId(questionId, quizId, userId);
    }


}
