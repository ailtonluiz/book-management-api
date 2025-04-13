package com.ailtonluiz.bookmanager.core.util.controller;

import com.ailtonluiz.bookmanager.api.dto.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final String SECRET_KEY = "minhaChaveSecretaSuperSegura";

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        // Valide o usuário e a senha (exemplo simples)
        if ("admin".equals(authRequest.getUsername()) && "1234".equals(authRequest.getPassword())) {
            return Jwts.builder()
                    .setSubject(authRequest.getUsername()) // Nome do usuário
                    .setIssuedAt(new Date()) // Data de emissão
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiração em 10 horas
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Assinatura
                    .compact(); // Gera o token
        }
        throw new RuntimeException("Usuário ou senha inválidos!");
    }
}

