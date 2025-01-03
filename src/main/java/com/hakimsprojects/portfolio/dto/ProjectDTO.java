package com.hakimsprojects.portfolio.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String link;
    private List<ImageProjectDTO> galleryImages;
}
