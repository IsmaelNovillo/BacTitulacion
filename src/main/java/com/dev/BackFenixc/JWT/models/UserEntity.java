package com.dev.BackFenixc.JWT.models;

import com.dev.BackFenixc.entity.Producto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "usuarios")
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id ;
    @Email
    @NotBlank
    private String email ;
    @NotBlank
    private String username;
    @NotBlank
    private String password ;
    @NotNull
    private int numero ;
    //para correo
    @Column (name = "Verification_code", length = 64)

    private String otp;

    private boolean active = false;

    private LocalDateTime otpGeneratedTime;
    //

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RolEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name= "user_roles", joinColumns = @JoinColumn (name= "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolEntity> rol ;

}
