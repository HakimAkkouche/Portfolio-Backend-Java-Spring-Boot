package com.hakimsprojects.portfolio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un projet dans le portfolio.
 * 
 * Cette classe est utilisée pour définir les propriétés d'un projet, 
 * y compris son titre, sa description, son lien, et sa galerie d'images associées.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

    /**
     * Identifiant unique du projet.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre du projet.
     */
    private String title;

    /**
     * Description détaillée du projet.
     * Utilise l'annotation @Lob pour stocker de grandes quantités de texte.
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Lien externe associé au projet (exemple : URL vers une démo ou un dépôt GitHub).
     */
    private String link;

    /**
     * Liste des images associées à ce projet.
     * Utilise une relation @OneToMany pour gérer les multiples images liées à un projet.
     * 
     * - `cascade = CascadeType.ALL` : Toute modification du projet entraîne une modification des images associées.
     * - `orphanRemoval = true` : Supprime automatiquement les images si elles ne sont plus associées au projet.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private List<ImageProject> galleryImages;

    /**
     * Ajoute une liste d'images à la galerie du projet.
     *
     * @param listImages la liste des images à ajouter
     */
    public void addImageToGallery(List<ImageProject> listImages) {
        this.galleryImages.addAll(listImages);
    }
}
