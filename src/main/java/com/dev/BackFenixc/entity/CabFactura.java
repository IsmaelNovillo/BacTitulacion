package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CabeceraFactura")
public class CabFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numfac")
    private Integer numfac;
    @Column(name = "rucEmpresa")
    private int rucEmpresa;

    @Column(name = "idCli")
    private int idCli;

    @Column(name = "fechaEmision")
    private Date fechaEmision;

    @Column(name = "totalFactura")
    private BigDecimal totalFactura;

    @Column(name = "descuentos")
    private BigDecimal descuentos;

    @Column(name = "iva")
    private BigDecimal iva;

    @Column(name = "totalPagar")
    private BigDecimal totalPagar;

    @ManyToOne (fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;





}
