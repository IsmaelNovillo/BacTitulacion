package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "planreservacion")
public class PlanReservacion {
    @Id
    @Column(name = "planid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Integer planid;

    @Column(name = "nombreplan")
    private String nombreplan;

    @Column(name = "descripcionplan")
    private String descripcionplan;

    @Column(name = "precioplan")
    private String precioplan;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotelid")
    private Hotel hotelid;

}