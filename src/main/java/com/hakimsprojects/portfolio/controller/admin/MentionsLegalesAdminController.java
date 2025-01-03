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

import com.hakimsprojects.portfolio.dto.MentionsLegalesDTO;
import com.hakimsprojects.portfolio.entity.MentionsLegales;
import com.hakimsprojects.portfolio.service.MentionsLegalesService;

/**
 * Contrôleur REST pour la gestion des mentions légales
 * au niveau de l'administration.
 * Fournit des endpoints pour récupérer et mettre à jour les mentions légales.
 */
@RestController
@RequestMapping("/api/admin/mentions-legales")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MentionsLegalesAdminController {
    
    private final MentionsLegalesService mentionsLegalesService;

    /**
     * Constructeur injectant le service de gestion des mentions légales.
     *
     * @param mentionsLegalesService le service utilisé pour manipuler les mentions légales.
     */
    public MentionsLegalesAdminController(MentionsLegalesService mentionsLegalesService) {
        super();
        this.mentionsLegalesService = mentionsLegalesService;
    }

    /**
     * Récupère les mentions légales.
     *
     * @return l'objet {@link MentionsLegales} contenant les données des mentions légales.
     */
    @GetMapping
    public MentionsLegalesDTO getPersonalInfo() {
        return mentionsLegalesService.getMentionsLegales();
    }

    /**
     * Met à jour les mentions légales avec une description fournie.
     *
     * @param description la nouvelle description des mentions légales.
     * @return une réponse HTTP confirmant la mise à jour ou signalant une erreur.
     * @throws IOException en cas d'erreur lors de la mise à jour.
     */
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateMentionsLegales(
            @RequestParam("description") String description
    ) throws IOException {
        mentionsLegalesService.updateInfo(description);
        return ResponseEntity.ok("Informations mises à jour avec succès.");
    }
}
