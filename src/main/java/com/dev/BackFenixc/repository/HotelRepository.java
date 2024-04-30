package com.dev.BackFenixc.repository;


import com.dev.BackFenixc.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
}