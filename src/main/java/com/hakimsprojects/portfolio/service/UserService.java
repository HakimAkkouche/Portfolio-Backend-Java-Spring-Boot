package com.hakimsprojects.portfolio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hakimsprojects.portfolio.dto.AppUserDTO;
import com.hakimsprojects.portfolio.entity.AppUser;
import com.hakimsprojects.portfolio.mappers.AppUserMapper;
import com.hakimsprojects.portfolio.security.repository.UserRepository;

/**
 * Service pour la gestion des utilisateurs.
 * 
 * Cette classe fournit des méthodes pour manipuler les données des utilisateurs,
 * y compris la création, la mise à jour, l'authentification et la récupération.
 */
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    /**
     * Constructeur du service utilisateur.
     * 
     * @param userRepository Le référentiel utilisateur pour accéder aux données de la base.
     * @param bCryptPasswordEncoder L'encodeur pour les mots de passe.
     */
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Récupère tous les utilisateurs de la base de données.
     * 
     * @return Une liste d'objets {@link AppUserDTO}.
     */
    public List<AppUserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(AppUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un utilisateur par son ID.
     * 
     * @param id L'ID de l'utilisateur.
     * @return L'utilisateur correspondant ou null s'il n'est pas trouvé.
     */
    public AppUserDTO getUser(Integer id) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return AppUserMapper.toDTO(user);
    }

    /**
     * Récupère un utilisateur par son nom d'utilisateur.
     * 
     * @param username Le nom d'utilisateur.
     * @return L'utilisateur correspondant ou null s'il n'est pas trouvé.
     */
    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     * 
     * Le mot de passe est encodé avant d'être sauvegardé, et le rôle par défaut est "ROLE_ADMIN".
     * 
     * @param user L'utilisateur à ajouter.
     * @return L'utilisateur ajouté avec ses informations mises à jour.
     */
    public AppUser addUser(AppUser user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");

        return userRepository.save(user);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     * 
     * @param user L'utilisateur avec les informations mises à jour.
     * @return L'utilisateur mis à jour.
     */
    public AppUserDTO updateUser(AppUserDTO userDTO) {
        AppUser user = AppUserMapper.toEntity(userDTO);
        AppUser updatedUser = userRepository.save(user);
        return AppUserMapper.toDTO(updatedUser);
    }

    /**
     * Authentifie un utilisateur en vérifiant son nom d'utilisateur et son mot de passe.
     * 
     * @param username Le nom d'utilisateur.
     * @param password Le mot de passe en clair.
     * @return true si les informations sont valides, sinon une exception est levée.
     * @throws UsernameNotFoundException Si l'utilisateur n'existe pas.
     * @throws BadCredentialsException Si le mot de passe est incorrect.
     */
    public boolean authenticate(String username, String password) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> 
            new UsernameNotFoundException("User does not exist in the database")
        );
        
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("The password is incorrect");
        }

        return true;
    }
}
