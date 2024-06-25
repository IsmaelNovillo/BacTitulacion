package com.dev.BackFenixc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CompraRequest {

        private List<Integer> productos;
        private BigDecimal valorTotal;


        // Constructor por defecto
        public CompraRequest() {}

        // Constructor con parámetros
        public CompraRequest(List<Integer> productos, BigDecimal valorTotal) {
            this.productos = productos;
            this.valorTotal = valorTotal;
        }

        // Getters y Setters
        public List<Integer> getProductos() {
            return productos;
        }

        public void setProductos(List<Integer> productos) {
            this.productos = productos;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
        }

}
