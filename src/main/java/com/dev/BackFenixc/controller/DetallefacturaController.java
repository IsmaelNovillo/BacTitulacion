package com.dev.BackFenixc.controller;


import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.repositories.UserRepository;
import com.dev.BackFenixc.JWT.security.util.EmailUtil;
import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Detallefactura;
import com.dev.BackFenixc.service.DetalleFacturaService;
import jakarta.mail.MessagingException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/detallefactura")
public class DetallefacturaController {
    @Autowired
    private DetalleFacturaService objService;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Detallefactura obj)throws DataException {
        return new ResponseEntity<>(objService.save(obj), HttpStatus.OK);
    }
    @PostMapping("/verify/{id}")
    @PreAuthorize("hasRole('EMPRENDEDOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public String verify(@PathVariable("id") int codigo)throws DataException {
        objService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setState("VERIFICADO");
            try {
                UserEntity user = userRepository.findByUsername(datosGuardados.getBuyerUsername()).orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado con : " + datosGuardados.getBuyerUsername()));;
                emailUtil.confirmadePurchase(user.getEmail(), datosGuardados.getProductName());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return objService.save(datosGuardados);
        });
        return "ORDEN VERIFICADA";
    }

    @GetMapping("/listar")
    public List<Detallefactura> listar() {
        return objService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detallefactura> obtenerPorId(@PathVariable("id") int codigo) {
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
