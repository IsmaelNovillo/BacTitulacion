package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.Hotel;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface Hotelservice {

    Hotel save (Hotel obj) throws DataException;
    List<Hotel> getAll();

    Optional<Hotel> getById(int id);

    Hotel update (Hotel objActualizado) throws DataException;

    void delete(int id)throws DataException;
}
