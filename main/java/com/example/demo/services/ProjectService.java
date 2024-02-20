package com.example.demo.services;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo.entities.Project;
import com.example.demo.repositories.ProjectRepo;


@Service
public class ProjectService {

	@Autowired
	public ProjectRepo projectRepo;
	 public ProjectService(ProjectRepo projectRepo) {
	        this.projectRepo = projectRepo;
	    }
	public Project createProject(Project project) {
		return projectRepo.save(project);
	}

	public Project getProjectById(Integer id) {
		return projectRepo.findById(id).orElse(null);
	}

	public List<Project> getAll() {
		return projectRepo.findAll();
	}
}
