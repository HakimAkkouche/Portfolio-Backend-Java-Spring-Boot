package com.hakimsprojects.portfolio.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service de gestion du stockage des fichiers.
 * 
 * Cette classe fournit des méthodes pour enregistrer et supprimer des fichiers
 * dans un répertoire de stockage spécifié.
 */
@Service
public class FileStorageService {

    private final String uploadDir = "uploads/"; // Répertoire de stockage des fichiers

    /**
     * Stocke un fichier sur le disque.
     * 
     * @param file     Le fichier à stocker.
     * @param fileName Le nom du fichier sous lequel il sera enregistré.
     * @return Le chemin du fichier enregistré.
     * @throws IOException Si une erreur survient lors de la création ou de l'écriture du fichier.
     */
    public String storeFile(MultipartFile file, String fileName) throws IOException {
        // Logs des informations sur le fichier
        System.out.println("original : " + file.getOriginalFilename());
        System.out.println("expected : " + fileName);

        // Détermine le chemin complet du fichier
        Path filePath = Paths.get(uploadDir + fileName);

        // Crée les répertoires nécessaires si inexistants
        Files.createDirectories(filePath.getParent());

        // Écrit le contenu du fichier
        Files.write(filePath, file.getBytes());

        // Retourne le chemin du fichier en remplaçant les séparateurs de chemins
        return filePath.toString().replace("\\", "/");
    }

    /**
     * Supprime un fichier du disque.
     * 
     * @param filePath Le chemin du fichier à supprimer.
     * @throws RuntimeException Si une erreur survient lors de la suppression du fichier.
     */
    public void deleteFile(String filePath) {
        // Convertit le chemin en utilisant les séparateurs adaptés au système
        Path path = Paths.get(filePath.replace("/", "\\"));

        try {
            // Logs le chemin absolu du fichier à supprimer
            System.out.println("path file to delete : " + path.toAbsolutePath().toString());

            // Supprime le fichier
            Files.delete(path);
        } catch (IOException e) {
            // Logs l'erreur en cas de problème
            System.out.println("error delete file : " + e.getLocalizedMessage());

            // Lève une exception personnalisée avec le message d'erreur
            throw new RuntimeException("Erreur lors de la suppression du fichier : " + filePath, e);
        }
    }
}
