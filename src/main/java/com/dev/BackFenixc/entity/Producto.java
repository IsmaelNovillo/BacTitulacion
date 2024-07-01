package com.dev.BackFenixc.entity;

import com.dev.BackFenixc.JWT.models.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String nomproducto;
    @NotNull
    @Column(name = "descripcionproducto")
    private String descripcionproducto;
    @NotNull
    @Column(name = "stockproducto")
    private Integer stockproducto;
    @NotNull
    @Column(name = "precioprducto")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precioprducto;

    @Column(name = "cloudinaryImageId")
    private String cloudinaryImageId;

    @Column (name = "imageUrl")
    private String imageUrl;

    @Column (name = "categoria")
    @NotNull
    private String categoria;
    //relacion con usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserEntity user;
    public String getUsername() {//obtener el nombre del usuario por medio del producto
        return user != null ? user.getUsername() : null;
    }
    public String getEmail() {
        return user != null ? user.getEmail() : null;
    }//obtener el email del usuario por medio del producto




}