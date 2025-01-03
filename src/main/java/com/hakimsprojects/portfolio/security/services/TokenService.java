package com.hakimsprojects.portfolio.security.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.hakimsprojects.portfolio.entity.AppUser;

/**
 * Service pour la gestion des tokens JWT.
 * 
 * Cette classe fournit une méthode pour générer un token JWT en fonction des informations 
 * d'authentification d'un utilisateur.
 */
@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    /**
     * Constructeur pour initialiser le service avec un encodeur JWT.
     *
     * @param jwtEncoder Encodeur JWT utilisé pour générer les tokens.
     */
    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Génère un token JWT basé sur les informations d'authentification fournies.
     *
     * @param authentication Objet contenant les détails de l'utilisateur authentifié.
     * @return Un token JWT encodé sous forme de chaîne de caractères.
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now(); // Heure actuelle pour les timestamps
        AppUser user = (AppUser) authentication.getPrincipal(); // Récupère l'utilisateur authentifié
        String role = user.getRole(); // Récupère le rôle de l'utilisateur

        // Création des revendications (claims) du token
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self") // Identifie le système comme émetteur du token
                .issuedAt(now) // Date et heure d'émission du token
                .expiresAt(now.plus(24, ChronoUnit.HOURS)) // Expiration du token après 24 heures
                .claim("scope", role) // Ajoute le rôle en tant que "scope"
                .build();
        
        // Encode les revendications et retourne le token encodé
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
