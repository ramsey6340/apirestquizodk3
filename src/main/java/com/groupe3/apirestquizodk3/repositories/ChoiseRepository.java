package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Choise;
import com.groupe3.apirestquizodk3.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiseRepository extends JpaRepository<Choise, Long> {
    List<Choise> findByQuestion(Question question);

    List<Choise> findByQuestionAndChoiseId(Question question, Long choiseId);
}
