package com.hakimsprojects.portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente une image associée à un projet dans la galerie.
 * 
 * Cette entité est utilisée pour stocker le chemin d'accès des images 
 * associées à un projet spécifique.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image_project")
public class ImageProject {

    /**
     * Identifiant unique de l'image.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Chemin d'accès au fichier image.
     */
    private String filePath;
}
