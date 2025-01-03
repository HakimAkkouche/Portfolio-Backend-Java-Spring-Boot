package com.hakimsprojects.portfolio.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hakimsprojects.portfolio.entity.AppUser;

import lombok.Data;

/**
 * Classe de transfert de données (DTO) utilisée pour les requêtes et réponses
 * génériques dans l'application.
 * 
 * Cette classe permet d'encapsuler les informations de réponse HTTP ou les
 * données liées aux utilisateurs pour une gestion centralisée.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    /**
     * Code d'état HTTP de la réponse.
     */
    private int statusCode;

    /**
     * Message d'erreur en cas de réponse non réussie.
     */
    private String error;

    /**
     * Message générique pour décrire la réponse.
     */
    private String message;

    /**
     * Jeton d'accès pour les utilisateurs authentifiés.
     */
    private String token;

    /**
     * Jeton de rafraîchissement pour renouveler le jeton d'accès.
     */
    private String refreshToken;

    /**
     * Date et heure d'expiration du jeton d'accès.
     */
    private String expirationTime;

    /**
     * Nom d'utilisateur associé à la requête ou réponse.
     */
    private String username;

    /**
     * Rôle de l'utilisateur dans l'application.
     */
    private String role;

    /**
     * Mot de passe de l'utilisateur. Doit être manipulé avec soin pour éviter
     * les violations de sécurité.
     */
    private String password;

    /**
     * Informations sur un utilisateur spécifique.
     */
    private AppUser ourUsers;

    /**
     * Liste des utilisateurs dans le contexte de la requête ou réponse.
     */
    private List<AppUser> ourUsersList;
}
