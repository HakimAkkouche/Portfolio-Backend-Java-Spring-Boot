package com.hakimsprojects.portfolio.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hakimsprojects.portfolio.dto.ProjectDTO;
import com.hakimsprojects.portfolio.service.ProjectService;

/**
 * Contrôleur REST pour gérer les projets au niveau de l'administration.
 * Fournit des endpoints pour les opérations CRUD sur les projets et leurs images associées.
 */
@RestController
@RequestMapping("/api/admin/projects")
public class ProjectAdminController {

    private final ProjectService service;

    /**
     * Constructeur injectant le service de gestion des projets.
     *
     * @param service le service utilisé pour gérer les projets.
     */
    public ProjectAdminController(ProjectService service) {
        this.service = service;
    }

    /**
     * Récupère tous les projets disponibles.
     *
     * @return une liste de projets.
     */
    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return service.getAllProjects();
    }

    /**
     * Récupère un projet spécifique par son ID.
     *
     * @param id l'identifiant du projet.
     * @return le projet correspondant à l'ID.
     */
    @GetMapping("/{id}")
    public ProjectDTO getProject(@PathVariable Long id) {
        return service.getProject(id);
    }

    /**
     * Enregistre un nouveau projet ou met à jour un projet existant.
     *
     * @param project les informations du projet à sauvegarder ou mettre à jour.
     * @return le projet sauvegardé ou mis à jour.
     */
    @PostMapping
    public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO) {
        return service.saveOrUpdate(projectDTO);
    }

    /**
     * Ajoute un nouveau projet avec des données multipart (y compris des images).
     *
     * @param title         le titre du projet.
     * @param description   la description du projet.
     * @param link          le lien associé au projet.
     * @param galleryImages une liste facultative d'images associées au projet.
     * @return une réponse confirmant l'ajout du projet.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProject(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("link") String link,
            @RequestParam(value = "galleryImages", required = false) List<MultipartFile> galleryImages
    ) {
        service.addProject(title, description, link, galleryImages);
        return ResponseEntity.ok("Projet ajouté avec succès");
    }

    /**
     * Supprime un projet spécifique par son ID.
     *
     * @param id l'identifiant du projet à supprimer.
     */
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        service.deleteProject(id);
    }

    /**
     * Met à jour un projet spécifique par son ID avec des données multipart.
     *
     * @param id            l'identifiant du projet.
     * @param title         le titre du projet.
     * @param description   la description du projet.
     * @param link          le lien associé au projet.
     * @param galleryImages une liste facultative d'images associées au projet.
     * @return une réponse confirmant la mise à jour ou signalant une erreur.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProject(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("link") String link,
            @RequestParam(value = "galleryImages", required = false) List<MultipartFile> galleryImages
    ) {
        try {
            service.updateProject(id, title, description, link, galleryImages);
            return ResponseEntity.ok("Projet mis à jour avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur : Projet non trouvé ou mise à jour impossible.");
        }
    }

    /**
     * Supprime une image spécifique par son ID.
     *
     * @param imageId l'identifiant de l'image à supprimer.
     * @return une réponse confirmant la suppression ou signalant une erreur.
     */
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable Long imageId) {
        try {
            service.deleteImage(imageId);
            return ResponseEntity.ok("Image supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'image");
        }
    }
}
