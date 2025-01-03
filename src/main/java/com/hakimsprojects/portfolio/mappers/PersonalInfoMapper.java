package com.hakimsprojects.portfolio.mappers;

import com.hakimsprojects.portfolio.dto.PersonalInfoDTO;
import com.hakimsprojects.portfolio.entity.PersonalInfo;

public class PersonalInfoMapper {
    public static PersonalInfoDTO toDTO(PersonalInfo personalInfo) {
        if (personalInfo == null) {
            return null;
        }
        PersonalInfoDTO dto = new PersonalInfoDTO();
        dto.setId(personalInfo.getId());
        dto.setEmail(personalInfo.getEmail());
        dto.setPhone(personalInfo.getPhone());
        dto.setName(personalInfo.getName());
        dto.setJobTitle(personalInfo.getJobTitle());
        dto.setLinkLinkedin(personalInfo.getLinkLinkedin());
        dto.setLinkGitHub(personalInfo.getLinkGitHub());
        dto.setProfileImage(personalInfo.getProfileImage());
        return dto;
    }

    public static PersonalInfo toEntity(PersonalInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(dto.getId());
        personalInfo.setEmail(dto.getEmail());
        personalInfo.setPhone(dto.getPhone());
        personalInfo.setName(dto.getName());
        personalInfo.setJobTitle(dto.getJobTitle());
        personalInfo.setLinkLinkedin(dto.getLinkLinkedin());
        personalInfo.setLinkGitHub(dto.getLinkGitHub());
        personalInfo.setProfileImage(dto.getProfileImage());
        return personalInfo;
    }
}
