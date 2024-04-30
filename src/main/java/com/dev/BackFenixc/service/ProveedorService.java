package com.dev.BackFenixc.service;


import com.dev.BackFenixc.entity.Proveedor;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    Proveedor save (Proveedor obj) throws DataException;
    List<Proveedor> getAll();

    Optional<Proveedor> getById(int id);

    Proveedor update (Proveedor objActualizado) throws DataException;

    void delete(int id)throws DataException;
}
