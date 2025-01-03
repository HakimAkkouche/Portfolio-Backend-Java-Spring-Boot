package com.hakimsprojects.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hakimsprojects.portfolio.entity.AppUser;

/**
 * Interface pour la gestion des opérations CRUD et des requêtes spécifiques 
 * pour l'entité {@link AppUser}.
 * 
 * Cette interface utilise {@link JpaRepository} pour fournir des fonctionnalités
 * prêtes à l'emploi pour manipuler les utilisateurs dans la base de données.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * 
     * @param username Le nom d'utilisateur recherché.
     * @return Un {@link Optional} contenant l'utilisateur si trouvé, sinon vide.
     */
    Optional<AppUser> findByUsername(String username);
}
