package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.entity.Hotel;
import com.dev.BackFenixc.service.serviceImpl.HotelServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {

    @Autowired
    private HotelServiceImpl objService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Hotel obj)throws DataException {
        return new ResponseEntity<>(objService.save(obj), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Hotel> listar() {
        return objService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerPorId(@PathVariable("id") int codigo) {
        return objService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
