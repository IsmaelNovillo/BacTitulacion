package com.dev.BackFenixc.service.serviceImpl;


import com.dev.BackFenixc.entity.Detallefactura;
import com.dev.BackFenixc.repository.DetallefacturaRepository;
import com.dev.BackFenixc.service.DetalleFacturaService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallefacturaServiceImpl implements DetalleFacturaService {

    @Autowired
    private DetallefacturaRepository repo;


    @Override
    public Detallefactura save(Detallefactura obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<Detallefactura> getAll() {
        return (List<Detallefactura>) repo.findAll();
    }

    @Override
    public Optional<Detallefactura> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Detallefactura update(Detallefactura objActualizado) throws DataException {
        return repo.save(objActualizado);
    }

    @Override
    public void delete(int id) throws DataException {
        repo.deleteById(id);
    }
}
