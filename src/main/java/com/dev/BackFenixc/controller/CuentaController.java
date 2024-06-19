package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.repositories.UserRepository;
import com.dev.BackFenixc.JWT.security.jwt.JwtUtils;
import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Cuenta;
import com.dev.BackFenixc.service.serviceImpl.CuentaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("/api/v1/cuenta")

public class CuentaController {

    @Autowired
    private CuentaServiceImpl cuentaService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Cuenta cuenta, HttpServletRequest request)throws DataException {
        String token = request.getHeader("Authorization").substring(7);

        // Extraer el nombre de usuario del token
        String username = jwtUtils.getUserFromToken(token);

        // Buscar el usuario en la base de datos
        UserEntity user = userRepository.findByUsername(username).orElseThrow(null);

        cuenta.setIduser(user);
        return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<Cuenta> listar() {
        return cuentaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerPorId(@PathVariable("id") int codigo) {
        return cuentaService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Cuenta cuenta) throws DataException{


        return (ResponseEntity<Cuenta>) cuentaService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setNumCuenta(cuenta.getNumCuenta());
            datosGuardados.setTipCuenta(cuenta.getTipCuenta().toUpperCase());
            datosGuardados.setBanco(cuenta.getBanco().toUpperCase());
            datosGuardados.setNombre(cuenta.getNombre());
            datosGuardados.setApellido(cuenta.getApellido());



            Cuenta datosActualizados = null;
            try{
                datosActualizados=cuentaService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        Cuenta cuenta = cuentaService.getById(codigo).orElse(null);
        cuentaService.delete(cuenta);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }
}
