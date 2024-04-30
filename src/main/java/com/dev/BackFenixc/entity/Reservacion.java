package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reservacion")
public class Reservacion {



        @Id
        @Column(name = "reservacionid", nullable = false)
        private Integer id;

        @Column(name = "fechainicioreservacion")
        private LocalDate fechainicioreservacion;

        @Column(name = "fechafinreservacion", length = 10)
        private String fechafinreservacion;

        @ManyToMany(fetch = FetchType.EAGER, targetEntity = PlanReservacion.class, cascade = CascadeType.PERSIST)
        @JoinTable(name= "reservacion_plan", joinColumns = @JoinColumn (name= "reservacion_id"), inverseJoinColumns = @JoinColumn(name = "id_planreser"))
        private Set<PlanReservacion> planid ;


}
