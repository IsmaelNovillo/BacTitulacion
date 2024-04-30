package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    @Column(name = "idproveedor")
    private Integer id;

    @Column(name = "nomproveedor")
    private String nomproveedor;

    @Column(name = "telefproveedor")
    private String telefproveedor;

    @Column(name = "correoproveedor")
    private String correoproveedor;

    @Column(name = "direccionproveedor")
    private String direccionproveedor;

    @Column(name = "ciuproveedor")
    private String ciuproveedor;

    @Column(name = "paisproveedor")
    private String paisproveedor;


    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Producto.class, cascade = CascadeType.PERSIST)
    @JoinTable(name= "proveedor_producto", joinColumns = @JoinColumn (name= "id_proveedor"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private Set<Producto> idproducto ;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idempresa")
    private Empresa idempresa;

}