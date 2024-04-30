package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "tipo_produto")
public class TipoProducto {
    @Id
    @Column(name = "idtipoproducto", nullable = false)
    private Integer id;

    @Column(name = "nombretipoproducto", length = 100)
    private String nombretipoproducto;

    @Column(name = "fechaElabtipoproducto")
    private Date fechaElab;

    @Column(name = "fechaExptipoproducto")
    private Date fechaExp;


}