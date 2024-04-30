package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "detalle_producto")
public class DetalleProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalleproductoid", nullable = false)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "disponibilidad", length = 100)
    private String disponibilidad;

    @OneToOne (fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn (name = "idproducto")
    private Producto idproducto;

}