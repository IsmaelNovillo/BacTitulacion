package com.dev.BackFenixc.JWT.models;

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
@Table(name = "roles")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Enumerated(EnumType.STRING)
    private ERol rol;
}
