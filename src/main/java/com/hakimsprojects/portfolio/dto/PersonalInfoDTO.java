package com.hakimsprojects.portfolio.dto;

import lombok.Data;

@Data
public class PersonalInfoDTO {
    private Long id;
    private String email;
    private String phone;
    private String name;
    private String jobTitle;
    private String linkLinkedin;
    private String linkGitHub;
    private String profileImage;
}
