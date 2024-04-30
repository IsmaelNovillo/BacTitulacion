package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Cliente;
import com.dev.BackFenixc.service.serviceImpl.ClienteServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("/api/v1/client")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente)throws DataException {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable("id") int codigo) {
        return clienteService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Cliente cliente) throws DataException{


        return (ResponseEntity<Cliente>) clienteService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setRucCliente(cliente.getRucCliente());
            datosGuardados.setNomCliente(cliente.getNomCliente().toUpperCase());
            datosGuardados.setApelCliente(cliente.getApelCliente().toUpperCase());
            datosGuardados.setDirCliente(cliente.getDirCliente());
            datosGuardados.setTelCliente(cliente.getTelCliente());
            datosGuardados.setMailCliente(cliente.getMailCliente());



            Cliente datosActualizados = null;
            try{
                datosActualizados=clienteService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        Cliente cliente = clienteService.getById(codigo).orElse(null);
        clienteService.delete(cliente);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }
}
