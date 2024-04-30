package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.CabFactura;
import com.dev.BackFenixc.repository.CabFacturaRepository;
import com.dev.BackFenixc.service.CabFacturaService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabFacturaServiceImpl implements CabFacturaService {
    @Autowired
    private CabFacturaRepository cabFacturaRepository;

    @Override
    public CabFactura save(CabFactura cabFactura) throws DataException {
        return cabFacturaRepository.save(cabFactura);
    }

    @Override
    public List<CabFactura> getAll() {
        return (List<CabFactura>) cabFacturaRepository.findAll();
    }

    @Override
    public Optional<CabFactura> getById(int id) {
        return cabFacturaRepository.findById(id);
    }

    @Override
    public void delete(CabFactura cabFactura) {
        cabFacturaRepository.delete(cabFactura);
    }
}
