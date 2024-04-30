package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.TipoProducto;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface TipoProductoService {

    TipoProducto save (TipoProducto obj) throws DataException;
    List<TipoProducto> getAll();

    Optional<TipoProducto> getById(int id);

    TipoProducto update (TipoProducto objActualizado) throws DataException;

    void delete(int id)throws DataException;
}
