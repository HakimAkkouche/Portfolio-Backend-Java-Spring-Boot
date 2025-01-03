package com.hakimsprojects.portfolio.mappers;

import com.hakimsprojects.portfolio.dto.AppUserDTO;
import com.hakimsprojects.portfolio.entity.AppUser;

public class AppUserMapper {
    public static AppUserDTO toDTO(AppUser appUser) {
        if (appUser == null) {
            return null;
        }
        AppUserDTO dto = new AppUserDTO();
        dto.setId(appUser.getId());
        dto.setUsername(appUser.getUsername());
        dto.setRole(appUser.getRole());
        return dto;
    }

    public static AppUser toEntity(AppUserDTO dto) {
        if (dto == null) {
            return null;
        }
        AppUser appUser = new AppUser();
        appUser.setId(dto.getId());
        appUser.setUsername(dto.getUsername());
        appUser.setRole(dto.getRole());
        return appUser;
    }
}

