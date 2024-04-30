package com.dev.BackFenixc.repository;

import com.dev.BackFenixc.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReservacionRepository extends CrudRepository<Reservacion, Integer> {
}