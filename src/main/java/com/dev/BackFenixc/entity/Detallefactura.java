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
    @Column(name = "coddetalle")
    private Integer id;

    @Column(name = "numfactura")
    private Integer numfactura;

    @Column(name = "codproducto")
    private Integer codproducto;

    @Column(name = "catidad")
    private Integer catidad;

    @Column(name = "valorventaunitario")
    private Integer valorventaunitario;

    @Column(name = "totaldetalle")
    private BigDecimal totaldetalle;

    @OneToOne (fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "numfac")
    private CabFactura numfac;

}