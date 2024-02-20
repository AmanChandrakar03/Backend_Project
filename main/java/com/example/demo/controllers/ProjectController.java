package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Project;
import com.example.demo.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public ResponseEntity<Project> createProject(@RequestBody Project project) {
		System.out.println(project);
		// Custom Exception Handling (if needed)
		// if (project.getName() == null || project.getDate() == null ||
		// project.getName().isEmpty()) {
		// throw new CustomException("All fields are required");
		// }
		Project savedProject = projectService.createProject(project);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
	}

	@GetMapping("/{project_id}")
	public ResponseEntity<?> getProjectById(@PathVariable Integer project_id) {
		Project project = projectService.getProjectById(project_id);
		if (project == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found with ID: " + project_id);
		}
		return ResponseEntity.ok(project);
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.projectService.getAll());
	}
}
