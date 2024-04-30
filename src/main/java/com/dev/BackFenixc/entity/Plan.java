package com.dev.BackFenixc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    @Column(name = "idplan")
    private Integer idplan;

    @Column (name = "descripcionPlan")
    private String descripcionplan;

    @Column (name = "nomPlan")
    private String nomplan;

    @Column (name = "costo")
    private BigDecimal costo;

    @Column (name = "requisitosPlan")
    private String requisitosplan;


}
