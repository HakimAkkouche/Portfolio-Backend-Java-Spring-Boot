package com.hakimsprojects.portfolio.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.hakimsprojects.portfolio.dto.MentionsLegalesDTO;
import com.hakimsprojects.portfolio.entity.MentionsLegales;
import com.hakimsprojects.portfolio.mappers.MentionsLegalesMapper;
import com.hakimsprojects.portfolio.repository.MentionsLegalesRepository;

/**
 * Service pour gérer les mentions légales.
 * 
 * Cette classe fournit des méthodes pour récupérer et mettre à jour
 * les informations des mentions légales.
 */
@Service
public class MentionsLegalesService {
    
    private final MentionsLegalesRepository mentionsLegalesRepository;

    /**
     * Constructeur du service des mentions légales.
     * 
     * @param mentionsLegalesRepository Le référentiel des mentions légales.
     */
    public MentionsLegalesService(MentionsLegalesRepository mentionsLegalesRepository) {
        this.mentionsLegalesRepository = mentionsLegalesRepository;
    }

    /**
     * Récupère les mentions légales actuelles.
     * 
     * @return L'objet {@link MentionsLegales} contenant les mentions légales, ou {@code null} si aucune information n'est disponible.
     */
    public MentionsLegalesDTO getMentionsLegales() {
        MentionsLegales info = mentionsLegalesRepository.findAll().stream().findFirst().orElse(null);
        return MentionsLegalesMapper.toDTO(info);
    }

    /**
     * Met à jour les mentions légales.
     * 
     * Cette méthode met à jour la description des mentions légales dans la base de données.
     * Si aucune information n'existe, une nouvelle instance est créée.
     * 
     * @param description La nouvelle description des mentions légales.
     * @return L'objet {@link MentionsLegales} mis à jour.
     * @throws IOException Si une erreur survient lors de l'enregistrement.
     */
    public MentionsLegalesDTO updateInfo(String description) {
        MentionsLegales info = mentionsLegalesRepository.findAll().stream().findFirst().orElse(new MentionsLegales());
        info.setDescription(description);
        MentionsLegales updatedInfo = mentionsLegalesRepository.save(info);
        return MentionsLegalesMapper.toDTO(updatedInfo);
    }
}
