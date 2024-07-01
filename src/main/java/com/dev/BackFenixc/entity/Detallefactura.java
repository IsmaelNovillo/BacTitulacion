package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detallefactura")
public class Detallefactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coddetalle")
    private Integer id;
    @NotNull
    private String productName;
    @NotNull
    private String paymentProofUrl;
    @NotNull
    private String buyerUsername;
    @NotNull
    private String sellerUsername;
    @NotNull
    private String state;
    @NotNull
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private Producto producto;



}