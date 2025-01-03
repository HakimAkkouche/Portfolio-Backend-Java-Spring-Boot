package com.hakimsprojects.portfolio.controller.admin;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hakimsprojects.portfolio.dto.PersonalInfoDTO;
import com.hakimsprojects.portfolio.entity.PersonalInfo;
import com.hakimsprojects.portfolio.service.PersonalInfoService;

/**
 * Contrôleur REST pour la gestion des informations personnelles
 * au niveau de l'administration.
 * Fournit des endpoints pour récupérer et mettre à jour les données personnelles.
 */
@RestController
@RequestMapping("/api/admin/personal-info")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PersonalInfoAdminController {

    private final PersonalInfoService personalInfoService;

    /**
     * Constructeur injectant le service de gestion des informations personnelles.
     *
     * @param personalInfoService le service utilisé pour manipuler les données personnelles.
     */
    public PersonalInfoAdminController(PersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    /**
     * Récupère les informations personnelles.
     *
     * @return l'objet {@link PersonalInfo} contenant les données personnelles.
     */
    @GetMapping
    public PersonalInfoDTO getPersonalInfo() {
        return personalInfoService.getInfo();
    }

    /**
     * Met à jour les informations personnelles avec des données multipart (y compris les fichiers d'images).
     *
     * @param email           l'adresse email.
     * @param phone           le numéro de téléphone.
     * @param name            le nom complet.
     * @param jobTitle        le titre du poste.
     * @param linkLinkedin    le lien vers le profil LinkedIn.
     * @param linkGitHub      le lien vers le profil GitHub.
     * @param profileImage    une image de profil facultative.
     * @return une réponse HTTP confirmant la mise à jour ou signalant une erreur.
     * @throws IOException en cas d'erreur lors de la manipulation des fichiers.
     */
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePersonalInfo(
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("name") String name,
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("linkLinkedin") String linkLinkedin,
            @RequestParam("linkGitHub") String linkGitHub,
            @RequestParam(value = "profile_image", required = false) MultipartFile profileImage
    ) throws IOException {
        personalInfoService.updateInfo(email, phone, name, jobTitle, linkLinkedin, linkGitHub, profileImage);
        return ResponseEntity.ok("Informations mises à jour avec succès.");
    }
    
}
