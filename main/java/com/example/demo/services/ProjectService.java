package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Project;
import com.example.demo.repositories.ProjectRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {

	@Autowired
	public ProjectRepo projectRepo;

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
