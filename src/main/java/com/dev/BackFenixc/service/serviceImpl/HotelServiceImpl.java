package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Hotel;
import com.dev.BackFenixc.repository.HotelRepository;
import com.dev.BackFenixc.service.Hotelservice;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements Hotelservice {

    @Autowired
    HotelRepository repo;

    @Override
    public Hotel save(Hotel obj) throws DataException {
        return repo.save(obj);
    }

    @Override
    public List<Hotel> getAll() {
        return (List<Hotel>)repo.findAll() ;
    }

    @Override
    public Optional<Hotel> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Hotel update(Hotel objActualizado) throws DataException {
        return save(objActualizado);
    }

    @Override
    public void delete(int id) throws DataException {
        repo.deleteById(id);
    }
}
