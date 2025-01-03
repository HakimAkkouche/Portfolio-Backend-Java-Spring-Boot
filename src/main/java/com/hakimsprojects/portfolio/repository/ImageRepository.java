package com.hakimsprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hakimsprojects.portfolio.entity.ImageProject;


/**
 * Interface pour la gestion des opérations CRUD et des requêtes spécifiques 
 * pour l'entité {@link ImageProject}.
 * 
 * Cette interface utilise {@link JpaRepository} pour fournir des fonctionnalités
 * prêtes à l'emploi pour manipuler les utilisateurs dans la base de données.
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageProject, Long> {
}
