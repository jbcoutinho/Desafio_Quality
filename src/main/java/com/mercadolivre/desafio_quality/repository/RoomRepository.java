package com.mercadolivre.desafio_quality.repository;

import com.mercadolivre.desafio_quality.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
