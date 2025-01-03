package com.hakimsprojects.portfolio.controller.publiq;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakimsprojects.portfolio.dto.MentionsLegalesDTO;
import com.hakimsprojects.portfolio.service.MentionsLegalesService;


@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MentionsLegalesController {
	private final MentionsLegalesService service;
	public MentionsLegalesController(MentionsLegalesService service) {
		this.service = service;
	}

	@GetMapping("/mentions-legales")
	public MentionsLegalesDTO getMentionsLegales() {
		return service.getMentionsLegales();
	}
	
}