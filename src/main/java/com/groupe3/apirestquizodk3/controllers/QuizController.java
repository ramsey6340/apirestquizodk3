package com.groupe3.apirestquizodk3.controllers;

import com.groupe3.apirestquizodk3.models.Quiz;
import com.groupe3.apirestquizodk3.services.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class QuizController {
    @Autowired
    private  QuizService quizService;

    //Endpoint pour récupérer la liste de tous les quizzes.
    @Operation(summary = "ceci est la methode de l'endpoint pour recuperer la liste de tous les quizzes ")
    @GetMapping("quizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    // Endpoint pour récupérer un quiz par son identifiant.
    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz par son identifiant")
    @GetMapping("quizzes/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId) {
        Optional<Quiz> quizOptional = quizService.getQuizById(quizId);
        return quizOptional.map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint pour récupérer la liste de tous les quizzes d'un utilisateur.
    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer la liste de tous les quizzes d'un utilisateur")
    @GetMapping("users/{userId}/quizzes")
    public List<Quiz> getQuizzesByUserId(@PathVariable Long userId) {
        return quizService.getQuizzesByUserId(userId);
    }
    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz precis d'un utilisateur precis")
    @GetMapping("users/{userId}/quizzes/{quizId}")
    public ResponseEntity<Optional<Quiz>> getQuizByUserIdAndQuizId(@PathVariable Long userId, @PathVariable Long quizId) {
        Optional<Quiz> quiz = quizService.getQuizByUserIdAndQuizId(userId, quizId);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }
    //Endpoint pour créer un nouveau quiz.
    @Operation(summary = "Ceci est la methode de l'endpoint pour creer un quiz")
    @PostMapping("users/{userId}/quizzes")
    public Quiz createQuiz(@PathVariable Long userId, @RequestBody Quiz quiz) {
            return quizService.createQuiz(userId, quiz);
    }


    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz à travers son titre")
    @GetMapping(value = "quizzes", params = "title")
    public ResponseEntity<List<Quiz>> getQuizzesByTitle(@RequestParam("title") String title) {
        List<Quiz> quizzes = quizService.getQuizzesByTitle(title);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz à travers son Id")
    @GetMapping(value = "quizzes", params = "quizId")
    public ResponseEntity<Quiz> getQuizByQuizId(@RequestParam("quizId") Long quizId) {
        Optional<Quiz> quizOptional = quizService.getQuizByQuizId(quizId);
        return quizOptional.map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz à travers son nombre maximal de question")
    @GetMapping(value = "quizzes", params = "nbMaxQuestion")
    public ResponseEntity<List<Quiz>> getQuizzesByNbMaxQuestion(@RequestParam("nbMaxQuestion") int nbMaxQuestion) {
        List<Quiz> quizzes = quizService.getQuizzesByNbMaxQuestion(nbMaxQuestion);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz à travers sa portée")
    @GetMapping(value = "quizzes", params = "visibility")
    public ResponseEntity<List<Quiz>> getQuizzesByVisibility(@RequestParam("visibility") String visibility) {
        List<Quiz> quizzes = quizService.getQuizzesByVisibility(visibility);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @Operation(summary = "Ceci est la methode de l'endpoint pour recuperer un quiz à travers sa date de creation")
    @GetMapping(value = "quizzes", params = "creationDate")
    public ResponseEntity<List<Quiz>> getQuizzesByCreationDate(@RequestParam("creationDate") LocalDate creationDate) {
        List<Quiz> quizzes = quizService.getQuizzesByCreationDate(creationDate);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

}
