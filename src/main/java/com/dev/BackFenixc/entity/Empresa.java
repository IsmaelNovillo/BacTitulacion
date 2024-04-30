package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    @Column(name = "idempresa")
    private Integer idempresa;

    @Column(name = "nomepresa")
    private String nomepresa;

    @Column(name = "dirempresa")
    private String dirempresa;

    @Column(name = "ciudadempresa")
    private String ciudadempresa;

    @Column(name = "paisempresa")
    private String paisempresa;


    @OneToMany(mappedBy = "idempresa", cascade = CascadeType.ALL)
    private List<Proveedor> proveedor = new ArrayList<>();

}