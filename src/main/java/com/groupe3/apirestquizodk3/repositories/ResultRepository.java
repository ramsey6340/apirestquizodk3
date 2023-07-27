package com.groupe3.apirestquizodk3.repositories;

import com.group3.apirestquiz.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
