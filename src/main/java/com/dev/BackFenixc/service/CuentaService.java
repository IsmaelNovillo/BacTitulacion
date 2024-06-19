package com.dev.BackFenixc.service;


import com.dev.BackFenixc.entity.Cuenta;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    Cuenta save (Cuenta cuenta) throws DataException;

    List<Cuenta> getAll();

    Optional<Cuenta> getById(int id);


    void delete (Cuenta cuenta);
}
