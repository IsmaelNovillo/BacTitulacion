package com.dev.BackFenixc.entity;

import com.dev.BackFenixc.JWT.models.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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

    @Column(name = "descripcionproducto")
    private String descripcionproducto;

    @Column(name = "stockproducto")
    private Integer stockproducto;

    @Column(name = "precioprducto")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precioprducto;

    @Column(name = "cloudinaryImageId")
    private String cloudinaryImageId;

    @Column (name = "imageUrl")
    private String imageUrl;

    @Column (name = "categoria")
    private String categoria;
    /*
    @ManyToOne (fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn (name = "nombrecategoria")
    private TipoProducto nombrecategoria;*/

    /*
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = UserEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name= "usuario_producto",  joinColumns = @JoinColumn(name = "id_producto"),inverseJoinColumns = @JoinColumn (name= "user_id"))
    private Set<UserEntity> User_id ;*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserEntity user;
    public String getUsername() {
        return user != null ? user.getUsername() : null;
    }
    public String getEmail() {
        return user != null ? user.getEmail() : null;
    }




}