package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.entity.PlanReservacion;
import com.dev.BackFenixc.service.PlanReservacionService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/planreservacion")
public class PlanReservacionController {

    @Autowired
    private PlanReservacionService objService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody PlanReservacion obj)throws DataException {
        return new ResponseEntity<>(objService.save(obj), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<PlanReservacion> listar() {
        return objService.getAll();
    }



    @GetMapping("/{id}")
    public ResponseEntity<PlanReservacion> obtenerPorId(@PathVariable("id") int codigo) {
        return objService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
