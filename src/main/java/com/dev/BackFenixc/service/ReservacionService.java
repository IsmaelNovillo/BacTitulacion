package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.Reservacion;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface ReservacionService {


    Reservacion save (Reservacion obj) throws DataException;
    List<Reservacion> getAll();

    Optional<Reservacion> getById(int id);

    Reservacion update (Reservacion objActualizado) throws DataException;

    void delete(int id)throws DataException;
}
