package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.Imagen;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface ImagenService {

    Imagen save (Imagen imagen) throws DataException;

    List<Imagen> getAll();

    Optional<Imagen> getById(String id);


    void delete (Imagen imagen);


}
