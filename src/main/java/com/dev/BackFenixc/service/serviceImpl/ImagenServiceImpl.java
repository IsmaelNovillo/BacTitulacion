package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.entity.Imagen;
import com.dev.BackFenixc.repository.ImagenRepository;
import com.dev.BackFenixc.service.ImagenService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService {
    @Autowired
    private ImagenRepository imagenRepository;

    @Override
    public Imagen save(Imagen imagen) throws DataException {
        return imagenRepository.save(imagen);
    }

    @Override
    public List<Imagen> getAll() {
        return (List<Imagen>) imagenRepository.findAll();
    }

    @Override
    public Optional<Imagen> getById(String id) {
        return imagenRepository.findById(id);
    }

    @Override
    public void delete(Imagen imagen) {
        imagenRepository.delete(imagen);
    }
}
