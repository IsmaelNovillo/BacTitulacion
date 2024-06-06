package com.dev.BackFenixc.repository;


import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto,Integer> {


    Optional<Producto> findById (Integer id);
    List<Producto> findByCategoria(String categoria);
}
