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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria", nullable = false)
    private Integer id;

    @Column(name = "nombrecategoria", length = 100)
    private String nombrecategoria;



}