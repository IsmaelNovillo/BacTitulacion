package com.dev.BackFenixc.controller;


import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.repositories.UserRepository;
import com.dev.BackFenixc.JWT.security.jwt.JwtUtils;
import com.dev.BackFenixc.dominio.HttpResponse;
import com.dev.BackFenixc.entity.Producto;
import com.dev.BackFenixc.service.serviceImpl.ProductoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import static com.dev.BackFenixc.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;


@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    String [] category = new String[]{"ARTESANIAS", "ALIMENTOS", "ROPA", "OTROS"};

    @Autowired
    private ProductoServiceImpl productoService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody Producto producto, HttpServletRequest request)throws DataException {
        // Extraer el token del encabezado
        String token = request.getHeader("Authorization").substring(7);

        // Extraer el nombre de usuario del token
        String username = jwtUtils.getUserFromToken(token);

        // Buscar el usuario en la base de datos
        UserEntity user = userRepository.findByUsername(username).orElseThrow(null);

        producto.setUser(user);

        return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
    }

    @PostMapping("image/{id}")
    public ResponseEntity<?> uploadImage (@PathVariable("id") final Integer id, @RequestPart final MultipartFile file){
        this.productoService.uploadImage(id,file);
        return ResponseEntity.ok("Imagen cargada exitosamente");
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

    @GetMapping("/user")
    public ResponseEntity<Producto> findById(HttpServletRequest request) {
        // Extraer el token del encabezado
        String token = request.getHeader("Authorization").substring(7);

        // Extraer el nombre de usuario del token
        String username = jwtUtils.getUserFromToken(token);

        // Buscar el usuario en la base de datos
        UserEntity user = userRepository.findByUsername(username).orElseThrow(null);
        return productoService.findById((int) user.getId()).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarDatos(@PathVariable("id") int codigo, @RequestBody Producto producto) throws DataException{


        return (ResponseEntity<Producto>) productoService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setNomproducto(producto.getNomproducto().toUpperCase());
            datosGuardados.setDescripcionproducto(producto.getDescripcionproducto());
            datosGuardados.setPrecioprducto(producto.getPrecioprducto());
            datosGuardados.setStockproducto(producto.getStockproducto());
            //datosGuardados.setImagen(producto.getImagen());
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
