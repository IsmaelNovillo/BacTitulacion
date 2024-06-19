package com.dev.BackFenixc.service.serviceImpl;


import com.dev.BackFenixc.entity.Cuenta;
import com.dev.BackFenixc.repository.CuentaRepository;
import com.dev.BackFenixc.service.CuentaService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta save(Cuenta cuenta) throws DataException {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> getAll() {
        return (List<Cuenta>) cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> getById(int id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public void delete(Cuenta cuenta) {
        cuentaRepository.delete(cuenta);
    }
}
