package com.hakimsprojects.portfolio.controller.publiq;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakimsprojects.portfolio.dto.AboutMeDTO;
import com.hakimsprojects.portfolio.service.AboutMeService;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AboutMeController {
	private final AboutMeService service;

	public AboutMeController(AboutMeService service) {
		this.service = service;
	}

	@GetMapping("/about-me")
	public AboutMeDTO getAboutMeInfos() {
		return service.getInfo();
	}
	
}