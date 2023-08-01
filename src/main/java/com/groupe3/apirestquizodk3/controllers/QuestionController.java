package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.services.QuestionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class QuestionController {
    @Autowired
    public QuestionService questionService;
    @GetMapping("quiz/{quizId}/questions")
    public List<Question>  getQuizQuestions(@PathVariable Long quizId){
        return questionService.getQuizQuestions(quizId);

    }
    //peut on ajouter une question directement ???
    @PostMapping("questions")
    public  Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @GetMapping("questions/{questionId}")
    public Optional<Question> getQuestion(@PathVariable Long questionId){
        return questionService.getQuestion(questionId);

    }
    @GetMapping("users/{userId}/quizzes/{quizId}/questions")
    public List<Question> getQuizQuestionsForUser(@PathVariable Long userId, @PathVariable Long quizId){
        return questionService.getQuizQuestionsForUser(userId, quizId);
    }
    @PostMapping("users/{userId}/quizzes/{quizId}/questions")
    public Question addQuestionForQuiz(@PathVariable Long userId, @PathVariable Long quizId, @RequestBody Question question){
        return question;//questionService.addQuestionForQuiz(question, userId, quizId);
    }
    @GetMapping("users/{userId}/quizzes/{quizId}/questions/{questionId}")
    public Question getUserSpecificQuestionForSpecificQuiz(@PathVariable Long questionId, @PathVariable Long quizId, @PathVariable Long userId){
        return questionService.getUserSpecificQuestionForSpecificQuiz(questionId, quizId, userId);
    }


}
