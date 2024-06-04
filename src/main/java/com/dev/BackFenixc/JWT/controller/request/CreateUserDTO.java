package com.dev.BackFenixc.JWT.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserDTO {

    @Email
    @NotBlank
    private String email ;
    @NotBlank
    private String username;
    @NotBlank
    private String password ;
    @NotNull
    private int numero;

    private Set<String> rol;
}
