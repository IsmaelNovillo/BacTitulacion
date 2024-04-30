package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.DetalleProducto;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;


public interface DetalleProductoService {
    DetalleProducto save (DetalleProducto detalleProducto) throws DataException;


    List<DetalleProducto> getAll();

    Optional<DetalleProducto> getById(int id);


    void delete (DetalleProducto detalleProducto);
}
