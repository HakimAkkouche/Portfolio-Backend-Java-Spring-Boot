package com.hakimsprojects.portfolio.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hakimsprojects.portfolio.entity.AppUser;

/**
 * Implémentation de {@link UserDetails} pour représenter les informations d'un utilisateur
 * dans le contexte de Spring Security.
 * 
 * Cette classe est un adaptateur qui permet d'utiliser un objet {@link AppUser}
 * avec les mécanismes de sécurité de Spring Security.
 */
public class AppUserDetails implements UserDetails {

    private final AppUser user;

    /**
     * Constructeur pour initialiser l'objet {@link AppUserDetails} avec un utilisateur {@link AppUser}.
     *
     * @param user L'utilisateur à encapsuler.
     */
    public AppUserDetails(AppUser user) {
        this.user = user;
    }

    /**
     * Obtient les autorités (rôles) de l'utilisateur.
     * 
     * Cette méthode convertit le rôle unique de l'utilisateur en une autorité compréhensible
     * par Spring Security.
     * 
     * @return Une collection contenant les autorités de l'utilisateur.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     * 
     * @return Le mot de passe de l'utilisateur.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Obtient le nom d'utilisateur.
     * 
     * @return Le nom d'utilisateur.
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Les méthodes suivantes peuvent être implémentées si nécessaire pour répondre aux besoins de sécurité.
    @Override
    public boolean isAccountNonExpired() {
        return true; // Par défaut, le compte n'expire jamais.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Par défaut, le compte n'est jamais verrouillé.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Par défaut, les informations d'identification n'expirent jamais.
    }

    @Override
    public boolean isEnabled() {
        return true; // Par défaut, l'utilisateur est activé.
    }
}
