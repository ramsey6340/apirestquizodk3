package com.groupe3.apirestquizodk3.services;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.models.Quiz;
import com.groupe3.apirestquizodk3.repositories.QuestionRepository;
import com.groupe3.apirestquizodk3.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    public QuestionRepository questionRepository;
    @Autowired
    public QuizRepository quizRepository;
     public List<Question> getQuizQuestions(Long quizId){
         return questionRepository.findAllByQuizQuizId(quizId);
     }

     public  Question addQuestion(Question question){
         return questionRepository.save(question);
     }

    public Optional<Question> getQuestion(Long questionId) {
         return questionRepository.findById(questionId);
    }

    public List<Question> getQuizQuestionsForUser (Long userId, Long quizId){
        return questionRepository.findByQuizQuizIdAndQuizUserUserId(userId, quizId);
    }
    public Question addQuestionForQuiz(Question q, Long userId, Long quizId){
            Optional<Quiz> quiz = quizRepository.findByUserUserIdAndQuizId(userId, quizId);
            q.setQuiz(quiz.get());
            if (quiz.isEmpty()){
                System.out.println("quiz est null");
            }
            System.out.println(q.getText());
            return questionRepository.save(q);
    }
    public Question getUserSpecificQuestionForSpecificQuiz(Long quizId, Long userId, Long questionId){
        return questionRepository.findByquestionIdAndQuizQuizIdAndQuizUserUserId(questionId, quizId, userId);

    }


}
