package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.TipoProducto;
import com.dev.BackFenixc.service.serviceImpl.TipoProductoServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("/api/v1/tipoproducto")
public class    TipoProductoController {

    @Autowired
    private TipoProductoServiceImpl objService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody TipoProducto obj)throws DataException {
        return new ResponseEntity<>(objService.save(obj), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<TipoProducto> listar() {
        return objService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProducto> obtenerPorId(@PathVariable("id") int codigo) {
        return objService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        objService.delete(codigo);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
