package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.PlanReservacion;
import com.dev.BackFenixc.repository.PlanreservacionRepository;
import com.dev.BackFenixc.service.PlanReservacionService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanReservacionServiceImpl implements PlanReservacionService {

    @Autowired
    PlanreservacionRepository repo;

    @Override
    public PlanReservacion save(PlanReservacion obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<PlanReservacion> getAll() {
        return (List<PlanReservacion>) repo.findAll();
    }

    @Override
    public Optional<PlanReservacion> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public PlanReservacion update(PlanReservacion objActualizado) throws DataException {
        return null;
    }

    @Override
    public void delete(int id) throws DataException {

    }
}
