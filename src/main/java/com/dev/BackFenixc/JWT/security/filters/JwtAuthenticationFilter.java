package com.dev.BackFenixc.JWT.security.filters;

import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.security.jwt.JwtUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtUtils jwtUtils;
    public  JwtAuthenticationFilter (JwtUtils jwtUtils ){
        this.jwtUtils = jwtUtils;
    }
    @Override//intentar auntenticar
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UserEntity userEntity = null;
        String username = "";
        String password = "";
        try {
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = userEntity.getUsername();
            password = userEntity.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override//se autentifica de forma correcta
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        String token = jwtUtils.GenerateAccessToken(user.getUsername());
        response.addHeader("Authorization", token);
        //ROLES
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        //
        Map<String,Object> httpResponse = new HashMap<>();
        httpResponse.put( "token",token);
        httpResponse.put( "Message","Autentificacion correcta");
        httpResponse.put( "Username",user.getUsername());
        httpResponse.put("Roles", roles);



        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();


        super.successfulAuthentication(request, response, chain, authResult);
    }
}
