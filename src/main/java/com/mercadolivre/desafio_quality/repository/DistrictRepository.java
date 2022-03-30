package com.mercadolivre.desafio_quality.repository;

import com.mercadolivre.desafio_quality.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
