package com.hakimsprojects.portfolio.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hakimsprojects.portfolio.dto.AboutMeDTO;
import com.hakimsprojects.portfolio.entity.AboutMe;
import com.hakimsprojects.portfolio.mappers.AboutMeMapper;
import com.hakimsprojects.portfolio.security.repository.AboutMeRepository;

@Service
public class AboutMeService {

	private AboutMeRepository aboutMeRepository;
    private final FileStorageService fileStorageService;

	public AboutMeService(AboutMeRepository aboutMeRepository, FileStorageService fileStorageService) {
		this.aboutMeRepository = aboutMeRepository;
        this.fileStorageService = fileStorageService;
	}
	
	/**
     * Récupère les informations personnelles.
     * 
     * @return L'objet {@link AboutMeDTO} contenant les informations A propos, ou {@code null} si aucune information n'est trouvée.
     */
    public AboutMeDTO getInfo() {
    	AboutMe info = aboutMeRepository.findAll().stream().findFirst().orElse(null);
        return AboutMeMapper.toDTO(info);
    }

    /**
     * Met à jour les informations personnelles.
     * 
     * Cette méthode permet de mettre à jour les champs textuels ainsi que les fichiers d'images associées.
     * 
     * @param aboutText Le texte de la page A propos de moi.
     * @param presentationImage Le fichier image de profil (optionnel).
     * @return L'objet {@link AboutMeDTO} mis à jour.
     * @throws IOException Si une erreur survient lors du stockage des fichiers.
     */
    public AboutMeDTO updateInfo(String aboutText, MultipartFile presentationImage) throws IOException {

        AboutMe info = aboutMeRepository.findAll().stream().findFirst().orElse(new AboutMe());
        info.setAboutText(aboutText);
        if (presentationImage != null && !presentationImage.isEmpty()) {
            info.setPresentationImage("presentation.jpg");
            fileStorageService.storeFile(presentationImage, "presentation.jpg");
        }

        AboutMe updatedInfo = aboutMeRepository.save(info);
        return AboutMeMapper.toDTO(updatedInfo);
    }
}
