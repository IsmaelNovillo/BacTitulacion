package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Imagen;
import com.dev.BackFenixc.service.serviceImpl.ImagenServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/imagen")
public class ImagenController {

    @Autowired
    private ImagenServiceImpl imagenService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Imagen imagen)throws DataException {
        return new ResponseEntity<>(imagenService.save(imagen), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Imagen> listar() {
        return imagenService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> obtenerPorId(@PathVariable("id") String codigo) {
        return imagenService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Imagen> actualizarDatos(@PathVariable("id") String codigo, @RequestBody Imagen imagen) throws DataException{


        return (ResponseEntity<Imagen>) imagenService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setNombreimagen(imagen.getNombreimagen().toUpperCase());
            //agregar id por sting

            Imagen datosActualizados = null;
            try{
                datosActualizados=imagenService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") String codigo) {
        Imagen imagen = imagenService.getById(codigo).orElse(null);
        imagenService.delete(imagen);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }


}
