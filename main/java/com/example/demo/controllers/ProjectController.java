package com.example.demo.controllers;

import java.util.List;

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
@RequestMapping("/")
public class ProjectController {
	@Autowired
	public ProjectService projectService;

	@PostMapping("create")
	public ResponseEntity<Project> createProject(@RequestBody Project project) {
		System.out.println(project);
		Project savedProject = projectService.createProject(project);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
	}

	@GetMapping("{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
		Project project = projectService.getProjectById(id);
		if (project != null) {
			return ResponseEntity.ok(project);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("getAll")
	List<Project> getAll() {
		return projectService.getAll();
	}
}
