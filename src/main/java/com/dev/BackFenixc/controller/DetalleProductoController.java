package com.dev.BackFenixc.controller;


import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.DetalleProducto;
import com.dev.BackFenixc.service.serviceImpl.DetalleProductoServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/reproductively")
public class DetalleProductoController {


    @Autowired
    private DetalleProductoServiceImpl detalleProductoService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody DetalleProducto detalleProducto)throws DataException {
        return new ResponseEntity<>(detalleProductoService.save(detalleProducto), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<DetalleProducto> listar() {
        return detalleProductoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleProducto> obtenerPorId(@PathVariable("id") int codigo) {
        return detalleProductoService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleProducto> actualizarDatos(@PathVariable("id") int codigo, @RequestBody DetalleProducto detalleProducto) throws DataException{


        return (ResponseEntity<DetalleProducto>) detalleProductoService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setDescripcion(detalleProducto.getDescripcion().toUpperCase());
            datosGuardados.setDisponibilidad(detalleProducto.getDisponibilidad());



            DetalleProducto datosActualizados = null;
            try{
                datosActualizados=detalleProductoService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        DetalleProducto detalleProducto = detalleProductoService.getById(codigo).orElse(null);
        detalleProductoService.delete(detalleProducto);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }




}
