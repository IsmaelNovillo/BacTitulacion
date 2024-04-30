package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.PlanReservacion;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface PlanReservacionService {

    PlanReservacion save (PlanReservacion obj) throws DataException;

    List<PlanReservacion> getAll();

    Optional<PlanReservacion> getById(int id);

    PlanReservacion update (PlanReservacion objActualizado) throws DataException;

    void delete(int id)throws DataException;

}
