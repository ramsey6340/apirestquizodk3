package com.groupe3.apirestquizodk3.services;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.models.Quiz;
import com.groupe3.apirestquizodk3.models.Result;
import com.groupe3.apirestquizodk3.models.User;
import com.groupe3.apirestquizodk3.repositories.QuestionRepository;
import com.groupe3.apirestquizodk3.repositories.QuizRepository;
import com.groupe3.apirestquizodk3.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ResultService resultService;
    @Autowired
    private UserService userService;

     public List<Question> getQuestionsByQuizId(Long quizId){
         return questionRepository.findAllByQuizQuizId(quizId);
     }

    public Optional<Question> getQuestion(Long questionId) {
         return questionRepository.findById(questionId);
    }

    public List<Question> getQuestionsByUserIdAndQuizId (Long userId, Long quizId){
        return questionRepository.findByQuizQuizIdAndQuizUserUserId(quizId, userId);
    }
    public Question addQuestion(Question question, Long userId, Long quizId){
        Optional<Quiz> quiz = quizRepository.findByUserUserIdAndQuizId(userId, quizId);
        if (quiz.isPresent()){
            question.setQuiz(quiz.get());
            return questionRepository.save(question);
        }
        return null;
    }
    public Question getQuestionByIdAndUserIdAndQuizId(Long questionId, Long quizId, Long userId){
        return questionRepository.findByQuestionIdAndQuizQuizIdAndQuizUserUserId(questionId, quizId, userId);

    }

    public Optional<Question> getQuestionByRank(int rank) {
        return questionRepository.findByRank(rank);
    }
    public Optional<Question> getNextQuestion(Long userId, Long quizId) {
        Optional<Result> result = resultService.getResultByUserIdAndQuizIdAndStateFalse(userId, quizId);
        if (result.isEmpty()){
            Optional<User> user = userService.getUserById(userId);
            Optional<Quiz> quiz = quizService.getQuizById(quizId);
            if (user.isPresent() && quiz.isPresent()){
                Result newResult = new Result();
                newResult.setUser(user.get());
                newResult.setQuiz(quiz.get());;
                result = Optional.of(resultRepository.save(newResult));
            }
            else{
                return Optional.empty();
            }
        }

        int questionAnsweredSize = result.get().getQuestions().size(); // On recupère le nombre de question repondue
        int nbMaxQuestion = result.get().getQuiz().getNbMaxQuestion(); // On récupère le nombre maximal de question
        int rank = 0; // Le rank de la question à retourner
        if(questionAnsweredSize < nbMaxQuestion){
            rank = ++questionAnsweredSize;
        }
        else {
            result.get().setState(true);
            resultRepository.save(result.get());
        }
        return getQuestionByRank(rank);
    }

}
