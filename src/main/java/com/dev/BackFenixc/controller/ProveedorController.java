package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Proveedor;
import com.dev.BackFenixc.service.ProveedorService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService objService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Proveedor obj)throws DataException {
        return new ResponseEntity<>(objService.save(obj), HttpStatus.OK);
    }
    @GetMapping("/listar")
    public List<Proveedor> listar() {
        return objService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Proveedor obj) throws DataException{


        return (ResponseEntity<Proveedor>) objService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setNomproveedor(obj.getNomproveedor().toUpperCase());
            datosGuardados.setTelefproveedor(obj.getTelefproveedor());
            datosGuardados.setCorreoproveedor(obj.getCorreoproveedor());
            datosGuardados.setDireccionproveedor(obj.getDireccionproveedor());
            datosGuardados.setCiuproveedor(obj.getCiuproveedor());
            datosGuardados.setPaisproveedor(obj.getCiuproveedor());

            Proveedor datosActualizados = null;
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
