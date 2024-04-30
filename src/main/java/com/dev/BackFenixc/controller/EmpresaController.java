package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Empresa;
import com.dev.BackFenixc.service.serviceImpl.EmpresaServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaServiceImpl objService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Empresa obj)throws DataException{
        return new ResponseEntity<>(objService.save(obj), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Empresa> listar() {
        return objService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerPorId(@PathVariable("id") int codigo) {
        return objService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Empresa obj) throws DataException{


        return (ResponseEntity<Empresa>) objService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setNomepresa(obj.getNomepresa().toUpperCase());
            datosGuardados.setDirempresa(obj.getDirempresa());
            datosGuardados.setCiudadempresa(obj.getCiudadempresa());
            datosGuardados.setPaisempresa(obj.getPaisempresa());

            Empresa datosActualizados = null;
            try{
                datosActualizados=objService.update(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
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
