package com.hakimsprojects.portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente les informations personnelles de l'utilisateur du portfolio.
 * 
 * Cette entité est utilisée pour stocker les données personnelles, les liens professionnels, 
 * ainsi que des images et un texte enrichi décrivant l'utilisateur.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {

    /**
     * Identifiant unique des informations personnelles.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Adresse e-mail de l'utilisateur.
     */
    private String email;

    /**
     * Numéro de téléphone de l'utilisateur.
     */
    private String phone;

    /**
     * Nom complet de l'utilisateur.
     */
    private String name;

    /**
     * Intitulé du poste ou titre professionnel de l'utilisateur.
     */
    private String jobTitle;

    /**
     * Lien vers le profil LinkedIn de l'utilisateur.
     */
    private String linkLinkedin;

    /**
     * Lien vers le profil GitHub de l'utilisateur.
     */
    private String linkGitHub;


    /**
     * Chemin du fichier de l'image de profil de l'utilisateur.
     */
    private String profileImage;

}
