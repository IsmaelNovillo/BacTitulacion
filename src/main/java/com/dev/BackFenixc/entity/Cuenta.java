package com.dev.BackFenixc.entity;

import com.dev.BackFenixc.JWT.models.UserEntity;

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
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idCuenta")
    private Integer idCuenta;

    @Column(name = "numCuenta")
    private int numCuenta;

    @Column(name = "tipCuenta")
    private String tipCuenta;

    @Column (name = "Banco")
    private String banco;

    @Column (name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn (name= "iduser")
    private UserEntity iduser;





}
