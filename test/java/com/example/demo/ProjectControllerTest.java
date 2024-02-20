package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.ProjectController;
import com.example.demo.entities.Project;
import com.example.demo.repositories.ProjectRepo;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectRepo projectRepository;

	private List<Project> projects;

	@BeforeEach
	void setUp() {
		projects = new ArrayList<>();
		projects.add(new Project(1, "Project 1", LocalDate.now()));
		projects.add(new Project(2, "Project 2", LocalDate.now()));
		projects.add(new Project(3, "Grokking Algorithms", LocalDate.now()));
	}

	@Test
	void testGetAllProjects() throws Exception {
		when(projectRepository.findAll()).thenReturn(projects);

		mockMvc.perform(get("/projects").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3)).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Project 1")).andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].name").value("Project 2"));
	}


	@Test
	void testCreateProject() throws Exception {
	  
	    Project project = new Project(4, "Project", LocalDate.now());
	    when(projectRepository.save(any(Project.class))).thenReturn(project);
	 
	    mockMvc.perform(post("/projects")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("{\"name\":\"Project\",\"start_date\":\"2024-02-21\"}"))
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.id").exists())
	            .andExpect(jsonPath("$.name").value("Project"));
	}

	@Test
	void testGetProjectById_Exists() throws Exception {
		int projectId = 1;
		Project project = projects.get(0);
		when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

		mockMvc.perform(get("/projects/{projectId}", projectId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Project 1"));
	}
}
