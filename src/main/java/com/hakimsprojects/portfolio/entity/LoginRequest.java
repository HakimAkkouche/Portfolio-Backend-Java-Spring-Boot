package com.hakimsprojects.portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente une requête de connexion au système.
 * 
 * Cette entité est utilisée pour capturer et stocker les informations 
 * nécessaires pour l'authentification d'un utilisateur, telles que 
 * le nom d'utilisateur et le mot de passe.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class LoginRequest {

    /**
     * Identifiant unique de la requête de connexion.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom d'utilisateur utilisé pour la connexion.
     */
    private String username;

    /**
     * Mot de passe associé au nom d'utilisateur pour l'authentification.
     */
    private String password;
}
