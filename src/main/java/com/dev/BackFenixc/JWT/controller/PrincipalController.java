package com.dev.BackFenixc.JWT.controller;

import com.dev.BackFenixc.JWT.controller.request.CreateUserDTO;
import com.dev.BackFenixc.JWT.models.ERol;
import com.dev.BackFenixc.JWT.models.RolEntity;
import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.repositories.UserRepository;
import com.dev.BackFenixc.JWT.security.util.EmailUtil;
import com.dev.BackFenixc.JWT.security.util.OtpUtil;
import com.dev.BackFenixc.JWT.security.util.ResetUtil;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class PrincipalController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private EmailUtil emailUtil;
    @GetMapping("/hello")
    public String hello(){
        return "hello not secured ";
    }
    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "hello secured ";
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@RequestParam String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with email: " + email));
        UserDto userDto = new UserDto(userEntity.getName(), userEntity.getEmail());
        return ResponseEntity.ok(userDto);
    }


// DTO class
public class UserDto {
    private String name;
    private String email;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
    @PostMapping ("/create")
    public ResponseEntity<?> createUser (@Valid @RequestBody CreateUserDTO createUserDTO){
        String otp = otpUtil.generateOtp();

        try{
            emailUtil.sendOtpEmail(createUserDTO.getEmail(), otp);

        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException("No se genero codigo de activacion"+e);
        }


        Set<RolEntity> roles= createUserDTO.getRol().stream()
                .map(rol->RolEntity.builder()
                        .rol(ERol.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .otp(otp)
                .otpGeneratedTime(LocalDateTime.now())
                .rol(roles)//quemado cliente
                .build();
        userRepository.save(userEntity);
        return  ResponseEntity.ok(userEntity);


    }

    @PutMapping("/verify-account")
    public String verifyAccount(@RequestParam String email, @RequestParam String otp) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Este correo " + email + " no se encontro")
        );
        if (userEntity.getOtp().equals(otp) && Duration.between(userEntity.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (1000 * 60)) {
            userEntity.setActive(true);
            userRepository.save(userEntity);
            return "Correo confirmado";
        }
        return "Tiempo excedido del codigo, generelo otra vez";
    }


    @PostMapping("/send-reset")
    public String resetPass (@Valid @RequestBody ResetUtil resetUtil){
        UserEntity userEntity = userRepository.findByEmail(resetUtil.getEmail()).orElseThrow(
                ()-> new RuntimeException("Este usuario "+ resetUtil.getEmail()+ " no se encontro")
        );
        try{
            emailUtil.sendPassword(userEntity.getEmail(), userEntity.getPassword());
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException("No se genero codigo de restablecimiento de contraseña"+e);
        }

        return "correo enviado";
    }

    @PostMapping("/reset-password")
    public String resetPassword (String email, @RequestBody ResetUtil resetUtil){
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("Este usuario "+ email+ " no se encontro")
        );
        if (userEntity.isActive()){
            userEntity.setPassword(resetUtil.getPassword());
            userRepository.save(userEntity);
            return "Contraseña actualizada correctamente";
        }
        return "Tiempo excedido del codigo, generelo otra vez ";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser (@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado exitosamende".concat(id);
    }

}
