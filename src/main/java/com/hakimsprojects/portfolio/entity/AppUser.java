package com.hakimsprojects.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un utilisateur de l'application.
 * 
 * Cette classe est utilisée pour définir les attributs et le comportement d'un utilisateur,
 * ainsi que pour interagir avec la base de données via JPA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    /**
     * Identifiant unique de l'utilisateur.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nom d'utilisateur unique.
     * Doit être fourni et ne peut pas être nul.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Rôle de l'utilisateur (exemple : "ADMIN", "USER").
     * Ce champ est obligatoire.
     */
    @Column(nullable = false)
    private String role = "USER";

    /**
     * Mot de passe de l'utilisateur.
     * Ce champ est optionnel mais doit être manipulé avec précaution
     * pour des raisons de sécurité.
     */
    @Column(name = "password")
    private String password;
}
