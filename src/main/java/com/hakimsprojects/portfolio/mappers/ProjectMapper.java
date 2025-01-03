package com.hakimsprojects.portfolio.mappers;

import java.util.stream.Collectors;

import com.hakimsprojects.portfolio.dto.ProjectDTO;
import com.hakimsprojects.portfolio.entity.Project;

public class ProjectMapper {
    public static ProjectDTO toDTO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setLink(project.getLink());
        dto.setGalleryImages(project.getGalleryImages().stream()
            .map(ImageProjectMapper::toDTO)
            .collect(Collectors.toList()));
        return dto;
    }

    public static Project toEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setLink(dto.getLink());
        project.setGalleryImages(dto.getGalleryImages().stream()
            .map(ImageProjectMapper::toEntity)
            .collect(Collectors.toList()));
        return project;
    }
}
