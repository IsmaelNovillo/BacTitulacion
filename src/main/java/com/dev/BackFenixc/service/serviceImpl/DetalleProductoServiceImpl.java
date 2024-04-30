package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.DetalleProducto;
import com.dev.BackFenixc.repository.DetalleProductoRepository;
import com.dev.BackFenixc.service.DetalleProductoService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleProductoServiceImpl implements DetalleProductoService {
    @Autowired
    private DetalleProductoRepository detalleProductoRepository;


    @Override
    public DetalleProducto save(DetalleProducto detalleProducto) throws DataException {
        return detalleProductoRepository.save(detalleProducto);
    }

    @Override
    public List<DetalleProducto> getAll() {
        return (List<DetalleProducto>) detalleProductoRepository.findAll();
    }

    @Override
    public Optional<DetalleProducto> getById(int id) {
        return detalleProductoRepository.findById(id);
    }

    @Override
    public void delete(DetalleProducto detalleProducto) {
        detalleProductoRepository.delete(detalleProducto);
    }
}
