package com.mercadolivre.desafio_quality.repository;

import com.mercadolivre.desafio_quality.model.District;
import com.mercadolivre.desafio_quality.model.Ground;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroundRepository extends JpaRepository<Ground, Long> {
}
