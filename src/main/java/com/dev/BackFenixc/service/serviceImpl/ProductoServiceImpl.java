package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Producto;
import com.dev.BackFenixc.repository.ProductoRepository;
import com.dev.BackFenixc.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getById(int id) {
        return productoRepository.findById(id);
    }



    @Override
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }
}
