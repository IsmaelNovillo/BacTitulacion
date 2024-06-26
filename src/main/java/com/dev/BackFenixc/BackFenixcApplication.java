package com.dev.BackFenixc;

import com.dev.BackFenixc.JWT.models.ERol;
import com.dev.BackFenixc.JWT.models.RolEntity;
import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackFenixcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackFenixcApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init () {
		return(args -> {
			UserEntity userEntity = UserEntity.builder()
					.email("josue.novillo@epn.edu.ec")
					.username("ismael")
					.password(passwordEncoder.encode("1234"))
					.rol(Set.of(RolEntity.builder().rol(ERol.valueOf(ERol.ADMIN.name())).build()))
					.build();
			UserEntity userEntity1 = UserEntity.builder()
					.email("martin.jimenez@epn.edu.ec")
					.username("martin")
					.password(passwordEncoder.encode("1234"))
					.rol(Set.of(RolEntity.builder().rol(ERol.valueOf(ERol.ADMIN.name())).build()))
					.build();


			userRepository.save(userEntity);
			userRepository.save(userEntity1);



		});

	}

}