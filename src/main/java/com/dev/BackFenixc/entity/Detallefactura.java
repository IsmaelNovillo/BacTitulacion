package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
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

    private String productName;

    private String paymentProofUrl;

    private String buyerUsername;

    private String sellerUsername;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private Producto producto;



}