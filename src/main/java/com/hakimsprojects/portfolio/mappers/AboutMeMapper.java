package com.hakimsprojects.portfolio.mappers;

import com.hakimsprojects.portfolio.dto.AboutMeDTO;
import com.hakimsprojects.portfolio.entity.AboutMe;

public class AboutMeMapper {
    public static AboutMeDTO toDTO(AboutMe aboutMe) {
        if (aboutMe == null) {
            return null;
        }
        AboutMeDTO dto = new AboutMeDTO();
        dto.setId(aboutMe.getId());
        dto.setAboutText(aboutMe.getAboutText());
        dto.setPresentationImage(aboutMe.getPresentationImage());
        return dto;
    }

    public static AboutMe toEntity(AboutMeDTO dto) {
        if (dto == null) {
            return null;
        }
        AboutMe aboutMe = new AboutMe();
        aboutMe.setId(dto.getId());
        aboutMe.setAboutText(dto.getAboutText());
        aboutMe.setPresentationImage(dto.getPresentationImage());
        return aboutMe;
    }
}

