package com.hakimsprojects.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class AboutMe {

    /**
     * Identifiant unique des informations personnelles.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Texte enrichi décrivant l'utilisateur, stocké au format JSON.
     * Utilise l'annotation @Lob pour permettre de stocker une grande quantité de texte.
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String aboutText;
    /**
     * Chemin du fichier de l'image de presentation affichée sur la page d'accueil.
     */
    private String presentationImage;

}
