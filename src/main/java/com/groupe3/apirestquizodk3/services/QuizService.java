package com.groupe3.apirestquizodk3.services;

import com.groupe3.apirestquizodk3.models.Quiz;
import com.groupe3.apirestquizodk3.models.User;
import com.groupe3.apirestquizodk3.repositories.QuizRepository;
import com.groupe3.apirestquizodk3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private  QuizRepository quizRepository;
    @Autowired
    private UserRepository userRepository;

    //Récupèrer la liste de tous les quizzes.
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    //Récupèrer un quiz par son identifiant.
    public Optional<Quiz> getQuizById(Long quizId) {
        return quizRepository.findById(quizId);
    }

    // Récupèrer la liste de tous les quizzes d'un utilisateur.
    public List<Quiz> getQuizzesByUserId(Long userId) {
        return quizRepository.findByUserUserId(userId);
    }

    //Créer un nouveau quiz.

    public Quiz createQuiz(Quiz quiz, Long userId) {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()) {
            quiz.setUser(user.get());
            return quizRepository.save(quiz);
        } else return  null;

    }

    // Pour rechercher des quizzes par titre
    public List<Quiz> getQuizzesByTitle(String title) {
        return quizRepository.findByTitle(title);
    }

    // Pour rechercher un quiz par son identifiant (quizId)
    public Optional<Quiz> getQuizByQuizId(Long quizId) {
        return quizRepository.findById(quizId);
    }
    // Pour rechercher des quizzes par le nombre maximum de questions (nbMaxQuestion)
    public List<Quiz> getQuizzesByNbMaxQuestion(int nbMaxQuestion) {
        return quizRepository.findByNbMaxQuestion(nbMaxQuestion);
    }

    // Pour rechercher des quizzes par visibilité (public ou private)
    public List<Quiz> getQuizzesByVisibility(String visibility) {
        return quizRepository.findByVisibility(visibility);
    }

    // Pour rechercher des quizzes par date de création (creationDate)
    public List<Quiz> getQuizzesByCreationDate(LocalDate creationDate) {
        return quizRepository.findByCreationDate(creationDate);
    }

    public Optional<Quiz> getQuizzesByUserUserIdAndQuizId(Long userId, Long quizId) {
        return quizRepository.findByUserUserIdAndQuizId(quizId,userId);
    }

}
