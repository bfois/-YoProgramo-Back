/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Security;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generateToken(Authentication authentication){
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime()+ SecurityConstans.JWT_EXPIRATION);
    
    String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, SecurityConstans.JWT_SECRET)
            .compact();
    return token;
    }
    
    public String getUsernameFromJwt(String token){
    Claims claims = Jwts.parser()
            .setSigningKey(SecurityConstans.JWT_SECRET)
            .parseClaimsJws(token)
            .getBody();
    return claims.getSubject();
    }
    
    public boolean validateToken(String token){
    try {
    Jwts.parser().setSigningKey(SecurityConstans.JWT_SECRET).parseClaimsJws(token);
    return true;
    }catch (Exception ex){
    throw new AuthenticationCredentialsNotFoundException("JWT EXPIRO O ES INCORRECTO");
    }
    }
}
