package com.groupe3.apirestquizodk3.repositories;

import com.groupe3.apirestquizodk3.models.Choise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiseRepository extends JpaRepository<Choise, Long> {
}
