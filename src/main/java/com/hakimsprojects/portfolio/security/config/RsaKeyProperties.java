package com.hakimsprojects.portfolio.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Classe de configuration pour les propriétés des clés RSA.
 * 
 * Cette classe utilise les annotations Spring Boot pour injecter les clés RSA
 * à partir des propriétés de configuration définies dans le fichier de configuration 
 * (comme `application.yml` ou `application.properties`).
 * 
 * Les clés publiques et privées sont utilisées pour signer et vérifier les JWT.
 */
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {

    /**
     * Obtient la clé publique RSA.
     * 
     * @return La clé publique RSA.
     */
    public RSAPublicKey publicKey() {
        return publicKey;
    }

    /**
     * Obtient la clé privée RSA.
     * 
     * @return La clé privée RSA.
     */
    public RSAPrivateKey privateKey() {
        return privateKey;
    }
}

