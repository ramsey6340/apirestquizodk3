package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.Choise;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.services.ChoiseService;
import com.groupe3.apirestquizodk3.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ChoiseController {

    @Autowired
    private ChoiseService choiseService;

    @Autowired
    private QuestionService questionService;

    // Récupère la liste des choix pour une question spécifique
    @GetMapping("users/{userId}/quizzes/{quizId}/questions/{questionId}/choises")
    public List<Choise> getChoisesByQuestion(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId) {
        Question question = questionService.getQuestionByIdAndUserIdAndQuizId(userId, quizId, questionId);
        return choiseService.getChoisesByQuestion(question);
    }

    // Ajoute un choix pour une question spécifique
    @PostMapping("users/{userId}/quizzes/{quizId}/questions/{questionId}/choises")
    public Choise addChoiseForQuestion(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId, @RequestBody Choise choise) {
        Question question = questionService.getQuestionByIdAndUserIdAndQuizId(userId, quizId, questionId);
        return choiseService.addChoiseForQuestion(question, choise);
    }

    // Récupère un choix spécifique associé à une question spécifique
    @GetMapping("users/{userId}/quizzes/{quizId}/questions/{questionId}/choises/{choiseId}")
    public Choise getChoiseByIdAndQuestion(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId, @PathVariable Long choiseId) {
        Question question = questionService.getQuestionByIdAndUserIdAndQuizId(userId, quizId, questionId);
        return choiseService.getChoiseByIdAndQuestion(question, choiseId);
    }
}
