package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.CabFactura;
import com.dev.BackFenixc.service.serviceImpl.CabFacturaServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("/api/v1/cabfactura")
public class CabFacturaController {


    @Autowired
    private CabFacturaServiceImpl cabFacturaService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody CabFactura cabFactura)throws DataException {
        return new ResponseEntity<>(cabFacturaService.save(cabFactura), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<CabFactura> listar() {
        return cabFacturaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabFactura> obtenerPorId(@PathVariable("id") int codigo) {
        return cabFacturaService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<CabFactura> actualizarDatos(@PathVariable("id") int codigo, @RequestBody CabFactura cabFactura) throws DataException{


        return (ResponseEntity<CabFactura>) cabFacturaService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setRucEmpresa(cabFactura.getRucEmpresa());
            datosGuardados.setIdCli(cabFactura.getIdCli());
            datosGuardados.setFechaEmision(cabFactura.getFechaEmision());
            datosGuardados.setTotalFactura(cabFactura.getTotalFactura());
            datosGuardados.setDescuentos(cabFactura.getDescuentos());
            datosGuardados.setIva(cabFactura.getIva());
            datosGuardados.setTotalPagar(cabFactura.getTotalPagar());


            CabFactura datosActualizados = null;
            try{
                datosActualizados=cabFacturaService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        CabFactura cabFactura = cabFacturaService.getById(codigo).orElse(null);
        cabFacturaService.delete(cabFactura);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }
        //checar si viene de dominio o usar el del sistema
    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }

}
