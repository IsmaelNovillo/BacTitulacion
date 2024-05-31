package com.dev.BackFenixc.controller;


import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Producto;
import com.dev.BackFenixc.service.serviceImpl.ProductoServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Producto producto)throws DataException {
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable("id") int codigo) {
        return productoService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Producto producto) throws DataException{


        return (ResponseEntity<Producto>) productoService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setNomproducto(producto.getNomproducto().toUpperCase());
            datosGuardados.setPrecioprducto(producto.getPrecioprducto());
            datosGuardados.setStockproducto(producto.getStockproducto());
            datosGuardados.setImagen(producto.getImagen());
            //datosGuardados.setNombrecategoria(producto.getNombrecategoria());


            Producto datosActualizados = null;
            try{
                datosActualizados=productoService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        Producto producto = productoService.getById(codigo).orElse(null);
        productoService.delete(producto);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }
}
