package com.dev.BackFenixc.controller;



import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Plan;
import com.dev.BackFenixc.service.serviceImpl.PlanServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/plan")
public class PlanController {

    @Autowired

    private PlanServiceImpl planService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Plan plan)throws DataException {
        return new ResponseEntity<>(planService.save(plan), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Plan> listar() {
        return planService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> obtenerPorId(@PathVariable("id") int codigo) {
        return planService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Plan> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Plan plan) throws DataException{


        return (ResponseEntity<Plan>) planService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setDescripcionplan(plan.getDescripcionplan().toUpperCase());
            datosGuardados.setNomplan(plan.getNomplan());
            datosGuardados.setCosto(plan.getCosto());
            datosGuardados.setRequisitosplan(plan.getRequisitosplan());


            Plan datosActualizados = null;
            try{
                datosActualizados= planService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        Plan plan = planService.getById(codigo).orElse(null);
        planService.delete(plan);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }
}
