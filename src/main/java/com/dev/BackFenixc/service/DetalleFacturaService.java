package com.dev.BackFenixc.service;



import com.dev.BackFenixc.entity.Detallefactura;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface DetalleFacturaService {

    Detallefactura save (Detallefactura obj) throws DataException;
    List<Detallefactura> getAll();

    Optional<Detallefactura> getById(int id);

    Detallefactura update (Detallefactura objActualizado) throws DataException;

    void delete(int id)throws DataException;
}
