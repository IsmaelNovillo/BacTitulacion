package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Empresa;
import com.dev.BackFenixc.repository.EmpresaRepository;
import com.dev.BackFenixc.service.EmpresaService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository repo;

    @Override
    public Empresa save(Empresa obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<Empresa> getAll() {
        return (List<Empresa>) repo.findAll();
    }

    @Override
    public Optional<Empresa> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Empresa update(Empresa objActualizado) throws DataException {
        return repo.save(objActualizado);
    }

    @Override
    public void delete(int id) throws DataException {
        repo.deleteById(id);
    }
}
