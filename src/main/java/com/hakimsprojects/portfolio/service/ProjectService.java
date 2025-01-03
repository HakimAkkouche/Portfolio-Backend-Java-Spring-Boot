package com.hakimsprojects.portfolio.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hakimsprojects.portfolio.dto.ProjectDTO;
import com.hakimsprojects.portfolio.entity.ImageProject;
import com.hakimsprojects.portfolio.entity.Project;
import com.hakimsprojects.portfolio.mappers.ProjectMapper;
import com.hakimsprojects.portfolio.repository.ImageRepository;
import com.hakimsprojects.portfolio.repository.ProjectRepository;

/**
 * Service pour gérer les projets et leurs images associés.
 * 
 * Cette classe fournit des méthodes pour ajouter, mettre à jour, supprimer et récupérer des projets,
 * ainsi que pour gérer les images associées à ces projets.
 */
@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ImageRepository imageRepository;
    private final FileStorageService fileStorageService;

    /**
     * Constructeur pour le service des projets.
     * 
     * @param repository Le référentiel pour accéder aux données des projets.
     * @param imageRepository Le référentiel pour accéder aux données des images associées.
     * @param fileStorageService Le service de gestion des fichiers.
     */
    public ProjectService(ProjectRepository repository, ImageRepository imageRepository,
            FileStorageService fileStorageService) {
        this.repository = repository;
        this.imageRepository = imageRepository;
        this.fileStorageService = fileStorageService;
    }

    /**
     * Récupère tous les projets disponibles.
     * 
     * @return Une liste d'objets {@link ProjectDTO}.
     */
    public List<ProjectDTO> getAllProjects() {
        return repository.findAll().stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un projet par son ID.
     * 
     * @param id L'identifiant du projet.
     * @return L'objet {@link ProjectDTO} correspondant.
     */
    public ProjectDTO getProject(long id) {
        Project project = repository.findById(id).orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        return ProjectMapper.toDTO(project);
    }

    /**
     * Sauvegarde ou met à jour un projet.
     * 
     * @param project L'objet projet à sauvegarder ou mettre à jour.
     * @return L'objet projet sauvegardé ou mis à jour.
     */
    public ProjectDTO saveOrUpdate(ProjectDTO projectDTO) {
        Project project = ProjectMapper.toEntity(projectDTO);
        Project savedProject = repository.save(project);
        return ProjectMapper.toDTO(savedProject);
    }

    /**
     * Ajoute un nouveau projet avec ses images associées.
     * 
     * @param title Le titre du projet.
     * @param description La description du projet.
     * @param link Le lien du projet.
     * @param galleryImages La liste des fichiers image associés.
     */
    public void addProject(String title, String description, String link, List<MultipartFile> galleryImages) {
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setLink(link);

        if (galleryImages != null && !galleryImages.isEmpty()) {
            List<ImageProject> imageProjects = galleryImages.stream()
                    .map(file -> {
                        try {
                            String filePath = fileStorageService.storeFile(file, UUID.randomUUID() + "_" + file.getOriginalFilename());
                            return new ImageProject(null, filePath);
                        } catch (IOException e) {
                            throw new RuntimeException("Erreur lors du stockage de l'image : " + file.getOriginalFilename(), e);
                        }
                    })
                    .collect(Collectors.toList());
            project.setGalleryImages(imageProjects);
        }

        repository.save(project);
    }


    /**
     * Met à jour un projet existant avec de nouvelles informations et images.
     * 
     * @param id L'identifiant du projet à mettre à jour.
     * @param title Le nouveau titre du projet.
     * @param description La nouvelle description du projet.
     * @param link Le nouveau lien du projet.
     * @param galleryImages La liste des nouvelles images associées.
     */
    public void updateProject(Long id, String title, String description, String link, List<MultipartFile> galleryImages) {
        Project existingProject = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));

        existingProject.setTitle(title);
        existingProject.setDescription(description);
        existingProject.setLink(link);

        if (galleryImages != null && !galleryImages.isEmpty()) {
            List<ImageProject> updatedImages = galleryImages.stream()
                    .map(file -> {
                        try {
                            String filePath = fileStorageService.storeFile(file, UUID.randomUUID() + "_" + file.getOriginalFilename());
                            return new ImageProject(null, filePath);
                        } catch (IOException e) {
                            throw new RuntimeException("Erreur lors du stockage de l'image : " + file.getOriginalFilename(), e);
                        }
                    }).collect(Collectors.toList());
            existingProject.addImageToGallery(updatedImages);
        }

        repository.save(existingProject);
    }

    /**
     * Supprime un projet par son ID.
     * 
     * @param id L'identifiant du projet à supprimer.
     */
    public void deleteProject(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Project with id " + id + " does not exist.");
        }
    }

    /**
     * Supprime une image associée à un projet.
     * 
     * @param imageId L'identifiant de l'image à supprimer.
     */
    public void deleteImage(Long imageId) {
        ImageProject image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image introuvable"));

        fileStorageService.deleteFile(image.getFilePath());
        imageRepository.deleteById(imageId);
    }
}
