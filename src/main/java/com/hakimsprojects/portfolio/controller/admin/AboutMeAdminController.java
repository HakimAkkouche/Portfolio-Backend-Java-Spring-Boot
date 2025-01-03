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

import com.hakimsprojects.portfolio.dto.AboutMeDTO;
import com.hakimsprojects.portfolio.service.AboutMeService;

@RestController
@RequestMapping("/api/admin/about-me")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AboutMeAdminController {

    private final AboutMeService aboutMeService;
    
    /**
     * Constructeur injectant le service de gestion des informations personnelles.
     *
     * @param personalInfoService le service utilisé pour manipuler les données personnelles.
     */
    public AboutMeAdminController(AboutMeService aboutMeService) {
        this.aboutMeService = aboutMeService;
    }
    


	@GetMapping
	public AboutMeDTO getAboutMeInfos() {
		return aboutMeService.getInfo();
	}
	
	/**
     * Met à jour les informations personnelles avec des données multipart (y compris les fichiers d'images).
     *
     * @param aboutText       le texte de présentation.
     * @param presentationImage une image de presentation facultative.
     * @return une réponse HTTP confirmant la mise à jour ou signalant une erreur.
     * @throws IOException en cas d'erreur lors de la manipulation des fichiers.
     */
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAboutMe(
            @RequestParam("about_text") String aboutText,
            @RequestParam(value = "presentation_image", required = false) MultipartFile presentationImage
    ) throws IOException {

        aboutMeService.updateInfo(aboutText, presentationImage);
        return ResponseEntity.ok("Informations mises à jour avec succès.");
    }
}
