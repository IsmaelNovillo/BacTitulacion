package com.dev.BackFenixc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoCompra {
    private List<ProductoCompra> productos;

    // Getters y Setters

    public List<ProductoCompra> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCompra> productos) {
        this.productos = productos;
    }

    public static class ProductoCompra {
        private Integer id;
        private Integer cantidad;

        // Getters y Setters

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
}
