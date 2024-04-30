package com.dev.BackFenixc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @Column(name = "hotelid")
    private Integer id;

    @Column(name = "nombrehotel")
    private String nombrehotel;

    @Column(name = "direccionhotel", length = 100)
    private String direccionhotel;

    @Column(name = "ciudadhotel")
    private String ciudadhotel;

    @Column(name = "paishotel")
    private String paishotel;

}