package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.Empresa;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

    Empresa save (Empresa obj) throws DataException;
    List<Empresa> getAll();

    Optional<Empresa>getById(int id);

    Empresa update (Empresa objActualizado) throws DataException;

    void delete(int id)throws DataException;
}
