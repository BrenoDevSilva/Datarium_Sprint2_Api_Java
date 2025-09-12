package com.datarium.api.repository;

import com.datarium.api.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}