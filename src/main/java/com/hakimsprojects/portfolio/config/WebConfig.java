package com.hakimsprojects.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration de l'application pour gérer les ressources statiques.
 * Cette classe implémente {@link WebMvcConfigurer} pour personnaliser la gestion des ressources statiques
 * comme les fichiers téléversés.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure un gestionnaire de ressources pour les fichiers téléchargés.
     * Les fichiers accessibles via l'URL `/uploads/**` sont mappés au répertoire `uploads/` 
     * sur le système de fichiers.
     * Une période de mise en cache de 3600 secondes est définie pour ces ressources.
     *
     * @param registry Le registre utilisé pour configurer les gestionnaires de ressources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/")
                .setCachePeriod(3600);
    }
}
