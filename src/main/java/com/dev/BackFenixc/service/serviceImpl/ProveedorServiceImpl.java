package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Proveedor;
import com.dev.BackFenixc.repository.ProveedorRepository;
import com.dev.BackFenixc.service.ProveedorService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository repo;
    @Override
    public Proveedor save(Proveedor obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<Proveedor> getAll() {
        return (List<Proveedor>) repo.findAll();
    }

    @Override
    public Optional<Proveedor> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Proveedor update(Proveedor objActualizado) throws DataException {
        return repo.save(objActualizado);
    }

    @Override
    public void delete(int id) throws DataException {
        repo.deleteById(id);
    }
}
