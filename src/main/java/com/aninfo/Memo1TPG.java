package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.ProjectService;
import com.aninfo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2

public class Memo1TPG {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;


	public static void main(String[] args) {
		SpringApplication.run(Memo1TPG.class, args);
	}

	@PostMapping("/projects")
	public Project createProject(@RequestParam String name, @RequestParam String description, @RequestParam LocalDate startDate, @RequestParam LocalDate estimatedFinishDate, @RequestParam Long leaderId)
	{
		return projectService.createProject(name, description, startDate, estimatedFinishDate,leaderId);
	}

	@PostMapping("/projects/{projectId}/tasks")
	public Task createTask(@PathVariable Long projectId, @RequestParam String name, @RequestParam String description, @RequestParam Priority priority, @RequestParam Long estimatedDuration, @RequestParam LocalDate startDate, @RequestParam LocalDate estimatedFinishDate)
	{
		return taskService.createTask(projectId, name, description, priority, estimatedDuration, startDate, estimatedFinishDate);
	}

	@GetMapping("/projects")
	public Collection<Project> getProjects() {
		return projectService.findAll();
	}

	@GetMapping("/projects/{projectId}")
	public Project getProject(@PathVariable Long projectId) {
		return projectService.findById(projectId);
	}

	@PutMapping("/projects/{projectId}")
	public Project editProject(@PathVariable Long projectId, @RequestParam String name, @RequestParam String description, @RequestParam Status status, @RequestParam LocalDate estimatedFinishDate)
	{
		return projectService.editProject(projectId,name,description,status,estimatedFinishDate);
	}


	@DeleteMapping("/projects/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {
		projectService.deleteById(projectId);
	}


	@GetMapping("/projects/{projectId}/tasks")
	public Collection<Task> getTasksForProject(@PathVariable Long projectId) {
		return taskService.getTasksByProject(projectId);
	}

	@PutMapping("/projects/{projectId}/tasks/{taskId}")
	public Task editTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestParam String name, @RequestParam String description, @RequestParam Priority priority, @RequestParam Status status, @RequestParam Long estimatedDuration, @RequestParam LocalDate finishDate) {
		return taskService.editTask(projectId,taskId, name, description, priority, status, estimatedDuration, finishDate);
	}

	@DeleteMapping("/projects/{projectId}/tasks/{taskId}")
	public void deleteTask(@PathVariable Long taskId) {
		taskService.deleteById(taskId);
	}


	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
