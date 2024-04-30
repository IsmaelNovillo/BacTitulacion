package com.dev.BackFenixc.repository;


import com.dev.BackFenixc.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProveedorRepository extends CrudRepository<Proveedor,Integer> {

}
