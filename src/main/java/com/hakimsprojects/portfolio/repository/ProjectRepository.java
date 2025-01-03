package com.hakimsprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hakimsprojects.portfolio.entity.Project;

/**
 * Interface pour la gestion des opérations CRUD et des requêtes spécifiques 
 * pour l'entité {@link Project}.
 * 
 * Cette interface utilise {@link JpaRepository} pour fournir des fonctionnalités
 * prêtes à l'emploi pour manipuler les utilisateurs dans la base de données.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
