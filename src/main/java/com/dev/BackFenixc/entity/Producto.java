package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    @Column(name = "idproducto")
    private Integer id;

    @Column(name = "nomproducto")
    private String nomproducto;

    @Column(name = "precioprducto")
    private BigDecimal precioprducto;

    @Column(name = "stockproducto")
    private Integer stockproducto;

    @Column (name = "imagen")
    private String imagen;

    @Column (name = "categoria")
    private String categoria;
    /*
    @ManyToOne (fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn (name = "nombrecategoria")
    private TipoProducto nombrecategoria;*/




}