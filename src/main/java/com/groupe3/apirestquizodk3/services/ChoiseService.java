package com.groupe3.apirestquizodk3.services;

import com.groupe3.apirestquizodk3.models.Choise;
import com.groupe3.apirestquizodk3.models.Question;
import com.groupe3.apirestquizodk3.repositories.ChoiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiseService {

    private final ChoiseRepository choiseRepository;

    @Autowired
    public ChoiseService(ChoiseRepository choiseRepository) {
        this.choiseRepository = choiseRepository;
    }

    //Méthode pour récupérer tous les choix associés à une question.
    public List<Choise> getChoisesByQuestion(Question question) {
        return choiseRepository.findByQuestion(question);
    }

    //Méthode pour ajouter un nouveau choix pour une question donnée.

    public Choise addChoiseForQuestion(Question question, Choise choise) {
        choise.setQuestion(question);
        return choiseRepository.save(choise);
    }

    //Méthode pour récupérer un choix spécifique associé à une question.

    public Choise getChoiseByIdAndQuestion(Question question, Long choiseId) {
        return (Choise) choiseRepository.findByQuestionAndChoiseId(question, choiseId);
    }
}
