package com.dev.BackFenixc.service.serviceImpl;


import com.dev.BackFenixc.entity.TipoProducto;
import com.dev.BackFenixc.repository.TipoProductoRepository;
import com.dev.BackFenixc.service.TipoProductoService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    @Autowired
    TipoProductoRepository repo;


    @Override
    public TipoProducto save(TipoProducto obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<TipoProducto> getAll() {

        return (List<TipoProducto>)repo.findAll();
    }

    @Override
    public Optional<TipoProducto> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public TipoProducto update(TipoProducto objActualizado) throws DataException {
        return repo.save(objActualizado);
    }

    @Override
    public void delete(int id) throws DataException {
        repo.deleteById(id);
    }
}
