package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "imagen")
public class Imagen {
    @Id
    @Column(name = "idimagen")
    private String idimagen;

    @Column(name = "nombreimagen")
    private String nombreimagen;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idproducto")
    private Producto idproducto;
}
