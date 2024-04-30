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

    @ManyToOne (fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idtipoproducto")
    private TipoProducto idtipoproducto;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Proveedor.class, cascade = CascadeType.PERSIST)
    @JoinTable(name= "producto_proveedor", joinColumns = @JoinColumn (name= "producto_id"), inverseJoinColumns = @JoinColumn(name = "id_proveedor"))
    private Set<Proveedor> idproveedor ;



}