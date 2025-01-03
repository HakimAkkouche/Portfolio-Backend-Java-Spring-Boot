package com.hakimsprojects.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
 * Représente les mentions légales associées au site du portfolio.
 * 
 * Cette entité est utilisée pour stocker les mentions légales nécessaires à des fins légales et réglementaires.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MentionsLegales {

    /**
     * Identifiant unique des mentions légales.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Texte complet des mentions légales.
     * Utilise l'annotation @Lob pour permettre le stockage d'un texte volumineux.
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
}
