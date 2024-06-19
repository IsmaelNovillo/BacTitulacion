package com.dev.BackFenixc.controller;

import com.dev.BackFenixc.JWT.security.util.EmailUtil;
import com.dev.BackFenixc.entity.CarritoCompra;
import com.dev.BackFenixc.entity.Producto;
import com.dev.BackFenixc.service.ProductoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compras")
public class CompraController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private EmailUtil emailUtil;

    @PutMapping("/compra")
    @PreAuthorize("hasAnyRole('EMPRENDEDOR','CLIENT')")
    public ResponseEntity<String> compra(@RequestBody CarritoCompra carritoCompra) throws MessagingException {
        List<CarritoCompra.ProductoCompra> productos = carritoCompra.getProductos();

        for (CarritoCompra.ProductoCompra productoCompra : productos) {
            Producto producto = productoService.getById(productoCompra.getId()).orElse(null);
            if (producto != null) {
                producto.setStockproducto(producto.getStockproducto() - productoCompra.getCantidad());
                productoService.save(producto);
                emailUtil.sendPurchase(producto.getEmail(), producto.getNomproducto());
            }
        }

        return new ResponseEntity<>("FELICIDADES POR LA COMPRA", HttpStatus.OK);
    }
}
