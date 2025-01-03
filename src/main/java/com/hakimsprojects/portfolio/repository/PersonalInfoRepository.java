package com.hakimsprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hakimsprojects.portfolio.entity.PersonalInfo;

/**
 * Interface pour la gestion des opérations CRUD et des requêtes spécifiques 
 * pour l'entité {@link PersonalInfo}.
 * 
 * Cette interface utilise {@link JpaRepository} pour fournir des fonctionnalités
 * prêtes à l'emploi pour manipuler les utilisateurs dans la base de données.
 */
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
}
