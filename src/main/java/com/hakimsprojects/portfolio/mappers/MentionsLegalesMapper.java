package com.hakimsprojects.portfolio.mappers;

import com.hakimsprojects.portfolio.dto.MentionsLegalesDTO;
import com.hakimsprojects.portfolio.entity.MentionsLegales;

public class MentionsLegalesMapper {
    public static MentionsLegalesDTO toDTO(MentionsLegales mentionsLegales) {
        if (mentionsLegales == null) {
            return null;
        }
        MentionsLegalesDTO dto = new MentionsLegalesDTO();
        dto.setId(mentionsLegales.getId());
        dto.setDescription(mentionsLegales.getDescription());
        return dto;
    }

    public static MentionsLegales toEntity(MentionsLegalesDTO dto) {
        if (dto == null) {
            return null;
        }
        MentionsLegales mentionsLegales = new MentionsLegales();
        mentionsLegales.setId(dto.getId());
        mentionsLegales.setDescription(dto.getDescription());
        return mentionsLegales;
    }
}
