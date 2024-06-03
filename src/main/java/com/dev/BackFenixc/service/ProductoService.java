package com.dev.BackFenixc.service;

import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.entity.Producto;
import org.hibernate.exception.DataException;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto save (Producto producto) throws DataException;

    List<Producto> getAll();

    Optional<Producto> getById(int id);

    Optional <Producto> findById (Integer id);


    void delete (Producto producto);




}
