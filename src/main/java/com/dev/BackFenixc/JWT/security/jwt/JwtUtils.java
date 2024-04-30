package com.dev.BackFenixc.JWT.security.jwt;

//class for the necessary methods to work  with token

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;



    ///generar token de accesos

    public String GenerateAccessToken (String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //Validar el Token de acceso

    public boolean isTokenValid (String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception e ){
            log.error("The token is invalid".concat(e.getMessage()));
            return false;
        }
    }

    //obtener el subject del token
    public String getUserFromToken (String token){
        return getClaim(token,Claims::getSubject);
    }

    //obtener un solo parametro del claim
    public <T> T getClaim (String token, Function<Claims,T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    //obtener todos los claims
    public Claims extractAllClaims (String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    ///obtener firma para el token

    public Key getSignatureKey (){
        byte[] KeyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(KeyBytes);
    }
}
