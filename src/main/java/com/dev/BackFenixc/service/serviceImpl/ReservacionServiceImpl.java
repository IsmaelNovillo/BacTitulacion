package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Reservacion;
import com.dev.BackFenixc.repository.ReservacionRepository;
import com.dev.BackFenixc.service.ReservacionService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservacionServiceImpl implements ReservacionService {

    @Autowired
    ReservacionRepository repo;

    @Override
    public Reservacion save(Reservacion obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<Reservacion> getAll() {
        return (List<Reservacion>) repo.findAll();
    }

    @Override
    public Optional<Reservacion> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Reservacion update(Reservacion objActualizado) throws DataException {
        return repo.save(objActualizado);
    }

    @Override
    public void delete(int id) throws DataException {
        repo.deleteById(id);
    }
}
