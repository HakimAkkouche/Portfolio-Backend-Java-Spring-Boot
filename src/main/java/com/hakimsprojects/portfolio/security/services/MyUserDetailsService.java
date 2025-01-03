package com.hakimsprojects.portfolio.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hakimsprojects.portfolio.entity.AppUser;
import com.hakimsprojects.portfolio.security.model.AppUserDetails;
import com.hakimsprojects.portfolio.security.repository.UserRepository;

/**
 * Service pour la gestion des détails des utilisateurs dans le cadre de Spring Security.
 * 
 * Cette classe implémente l'interface {@link UserDetailsService} pour fournir les informations 
 * d'utilisateur nécessaires à l'authentification et à l'autorisation.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructeur pour initialiser le service avec le référentiel des utilisateurs.
     * 
     * @param userRepository Le référentiel permettant d'accéder aux utilisateurs dans la base de données.
     */
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Charge les détails de l'utilisateur par son nom d'utilisateur.
     * 
     * @param username Le nom d'utilisateur pour lequel les détails doivent être récupérés.
     * @return Un objet {@link UserDetails} contenant les informations de l'utilisateur.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé dans la base de données.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isEmpty()) {
            return new AppUserDetails(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    /**
     * Compte le nombre total d'utilisateurs dans la base de données.
     * 
     * @return Le nombre d'utilisateurs.
     */
    public long countUsers() {
        return userRepository.count();
    }

    /**
     * Enregistre un nouvel utilisateur dans la base de données.
     * 
     * @param user L'utilisateur à enregistrer.
     */
    public void saveUser(AppUser user) {
        userRepository.save(user);
    }
}
