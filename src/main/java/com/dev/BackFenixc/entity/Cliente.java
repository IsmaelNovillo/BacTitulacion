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
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idCliente")
    private Integer idcliente;

    @Column(name = "rucCliente")
    private int rucCliente;

    @Column(name = "nomCliente")
    private String nomCliente;

    @Column (name = "apelCliente")
    private String apelCliente;

    @Column (name = "dirCliente")
    private String dirCliente;

    @Column(name = "telCliente")
    private int telCliente;

    @Column(name = "mailCliente")
    private String mailCliente;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn (name= "iduser")
    private UserEntity iduser;





}
