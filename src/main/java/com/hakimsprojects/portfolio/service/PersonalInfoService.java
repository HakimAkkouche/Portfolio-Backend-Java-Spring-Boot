package com.hakimsprojects.portfolio.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hakimsprojects.portfolio.dto.PersonalInfoDTO;
import com.hakimsprojects.portfolio.entity.PersonalInfo;
import com.hakimsprojects.portfolio.mappers.PersonalInfoMapper;
import com.hakimsprojects.portfolio.repository.PersonalInfoRepository;

/**
 * Service pour gérer les informations personnelles.
 * 
 * Cette classe fournit des méthodes pour récupérer et mettre à jour les informations personnelles,
 * ainsi que pour gérer les images associées.
 */
@Service
public class PersonalInfoService {

    private final PersonalInfoRepository repository;
    private final FileStorageService fileStorageService;

    /**
     * Constructeur pour le service des informations personnelles.
     * 
     * @param repository Le référentiel pour accéder aux données des informations personnelles.
     * @param fileStorageService Le service pour la gestion des fichiers.
     */
    public PersonalInfoService(PersonalInfoRepository repository, FileStorageService fileStorageService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    /**
     * Récupère les informations personnelles.
     * 
     * @return L'objet {@link PersonalInfoDTO} contenant les informations personnelles, ou {@code null} si aucune information n'est trouvée.
     */
    public PersonalInfoDTO getInfo() {
    	PersonalInfo info = repository.findAll().stream().findFirst().orElse(null);
    	System.out.println("infos : " + info);
        return PersonalInfoMapper.toDTO(info);
    }

    /**
     * Met à jour les informations personnelles.
     * 
     * Cette méthode permet de mettre à jour les champs textuels ainsi que les fichiers d'images associées.
     * 
     * @param email L'adresse email de la personne.
     * @param phone Le numéro de téléphone.
     * @param name Le nom de la personne.
     * @param jobTitle L'intitulé du poste.
     * @param linkLinkedin Le lien vers le profil LinkedIn.
     * @param linkGitHub Le lien vers le profil GitHub.
     * @param profileImage Le fichier image de profil (optionnel).
     * @return L'objet {@link PersonalInfoDTO} mis à jour.
     * @throws IOException Si une erreur survient lors du stockage des fichiers.
     */
    public PersonalInfoDTO updateInfo(String email, String phone, String name, String jobTitle, String linkLinkedin, 
            String linkGitHub, MultipartFile profileImage) throws IOException {

        PersonalInfo info = repository.findAll().stream().findFirst().orElse(new PersonalInfo());
        info.setEmail(email);
        info.setPhone(phone);
        info.setName(name);
        info.setJobTitle(jobTitle);
        info.setLinkLinkedin(linkLinkedin);
        info.setLinkGitHub(linkGitHub);

        if (profileImage != null && !profileImage.isEmpty()) {
            info.setProfileImage("profile.png");
            fileStorageService.storeFile(profileImage, "profile.png");
        }

        PersonalInfo updatedInfo = repository.save(info);
        return PersonalInfoMapper.toDTO(updatedInfo);
    }
}
