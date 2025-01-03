package com.hakimsprojects.portfolio.mappers;

import com.hakimsprojects.portfolio.dto.ImageProjectDTO;
import com.hakimsprojects.portfolio.entity.ImageProject;

public class ImageProjectMapper {
    public static ImageProjectDTO toDTO(ImageProject imageProject) {
        if (imageProject == null) {
            return null;
        }
        ImageProjectDTO dto = new ImageProjectDTO();
        dto.setId(imageProject.getId());
        dto.setFilePath(imageProject.getFilePath());
        return dto;
    }

    public static ImageProject toEntity(ImageProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        ImageProject imageProject = new ImageProject();
        imageProject.setId(dto.getId());
        imageProject.setFilePath(dto.getFilePath());
        return imageProject;
    }
}

