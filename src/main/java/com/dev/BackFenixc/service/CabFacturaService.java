package com.dev.BackFenixc.service;

import com.dev.BackFenixc.entity.CabFactura;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface CabFacturaService {
    CabFactura save (CabFactura cabFactura) throws DataException;

    List<CabFactura> getAll();

    Optional<CabFactura> getById(int id);


    void delete (CabFactura cabFactura);

}
