package com.groupe3.apirestquizodk3.services;
import com.groupe3.apirestquizodk3.models.Choise;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.models.Result;
import com.groupe3.apirestquizodk3.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    @Lazy
    // En utilisant @Lazy, Spring chargera le bean QuestionService uniquement lorsqu'il sera effectivement utilisé, évitant ainsi la dépendance cyclique au moment du démarrage de l'application.
    private QuestionService questionService;

    public List<Result>  getUserResultsForSpecificQuiz (Long userId, Long quizId){
        return resultRepository.findByUserUserIdAndQuizQuizId(userId, quizId);
    }

    public Result getSpecificResultForSpecificUserForSpecificQuiz(Long userId, Long quizId, Long resultId) {
        return resultRepository.findByResultIdAndUserUserIdAndQuizQuizId(userId, quizId, resultId);
    }

    public List<Result> getResultsForSpecificQuiz(Long quizId) {
        return resultRepository.findByQuizQuizId(quizId);
    }

    public Optional<Result> getSpecificResultForSpecificQuiz(Long quizId, Long resultId) {

        return resultRepository.findByResultIdAndQuizQuizId(quizId, resultId);
    }

    public List<Result> getResultsForSpecificQuizAndSpecificScore(Long quizId, Integer score) {
        return resultRepository.findByQuizQuizIdAndScore(quizId, score);
    }
    public Optional<Result> getResultByUserIdAndQuizIdAndStateFalse(Long userId, Long quizId) {
        return resultRepository.findByUserUserIdAndQuizQuizIdAndStateFalse(userId, quizId);
    }

    public List<Result> getResultsRanksForQuiz(Long quizId) {
        return resultRepository.findByQuizQuizId(quizId);
    }

    public Result respondQuestion(Long userId, Long quizId, int answer) {
        Optional<Question> question = questionService.getNextQuestion(userId, quizId);
        Optional<Result> result = getResultByUserIdAndQuizIdAndStateFalse(userId, quizId);
        if (question.isPresent() && result.isPresent()){
            Optional<Choise> choise = question.get().getChoises().stream().filter(c -> c.getRank()==answer).findFirst();
            if (choise.isPresent()){
                if (choise.get().isResponse()){
                    result.get().setScore(result.get().getScore()+question.get().getPoint());
                }
            }
            result.get().getQuestions().add(question.get());
            resultRepository.save(result.get());
            System.out.println(result.get().getQuestions().size());
            return result.get();
        }
        return null;
    }
    public Map<Integer, Map<String, String>> getMaxScoreResultsByUserAndQuiz(Long quizId) {
        List<Result> maxScoreResults = resultRepository.findMaxScoreResultsByUserAndQuiz(quizId);

        // Traiter les résultats pour les formater sous forme de JSON
        Map<Integer, Map<String, String>> formattedResults = new HashMap<>();
        int rank = 1;
        for (Result result : maxScoreResults) {
            Map<String, String> userData = new HashMap<>();
            userData.put("score", String.valueOf(result.getScore()));
            userData.put("name", result.getUser().getFirstName());
            formattedResults.put(rank, userData);
            rank++;
        }

        return formattedResults;
    }
    public Optional<Result> endGame(Long userId, Long quizId) {
        Optional<Result> result = resultRepository.findByUserUserIdAndQuizQuizIdAndStateFalse(userId, quizId);
        if(result.isPresent()){
            result.get().setState(true);
        }
        else {
            return null;
        }
        resultRepository.save(result.get());
        return result;
    }
}
