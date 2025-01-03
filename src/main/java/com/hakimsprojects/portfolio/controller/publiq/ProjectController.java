package com.hakimsprojects.portfolio.controller.publiq;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakimsprojects.portfolio.dto.ProjectDTO;
import com.hakimsprojects.portfolio.service.ProjectService;

@RestController
@RequestMapping("/api/public/projects")
public class ProjectController {
	private final ProjectService service;

	public ProjectController(ProjectService service) {
		this.service = service;
	}

	@GetMapping
	public List<ProjectDTO> getAllProjects() {
		return service.getAllProjects();
	}

	@GetMapping("/{id}")
	public ProjectDTO getProject(@PathVariable Long id) {
		return service.getProject(id);
	}
}
