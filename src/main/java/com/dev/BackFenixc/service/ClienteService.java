package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.Cliente;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente save (Cliente cliente) throws DataException;

    List<Cliente> getAll();

    Optional<Cliente> getById(int id);


    void delete (Cliente cliente);
}
