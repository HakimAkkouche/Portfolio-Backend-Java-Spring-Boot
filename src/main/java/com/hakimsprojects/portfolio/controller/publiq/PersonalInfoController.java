package com.hakimsprojects.portfolio.controller.publiq;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakimsprojects.portfolio.dto.PersonalInfoDTO;
import com.hakimsprojects.portfolio.service.PersonalInfoService;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PersonalInfoController {
	private final PersonalInfoService service;

	public PersonalInfoController(PersonalInfoService service) {
		this.service = service;
	}

	@GetMapping("/personal-info")
	public PersonalInfoDTO getPersonalInfo() {
		return service.getInfo();
	}
	
}