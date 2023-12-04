package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.ProjectService;
import com.aninfo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories

public class Memo1TPG {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;


	public static void main(String[] args) {
		SpringApplication.run(Memo1TPG.class, args);
	}

	@PostMapping("/projects")
	public Project createProject(@RequestBody ProjectCreationRequest project)
	{
		System.out.println(project);
		return projectService.createProject(project.getName(),
				project.getDescription(),
				LocalDate.parse(project.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				LocalDate.parse(project.getEstimatedFinishDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Long.parseLong(project.getLeaderId()));
	}

	@PostMapping("/projects/{projectId}/tasks")
	public Task createTask(@PathVariable Long projectId, @RequestBody TaskCreationRequest task)
	{
		return taskService.createTask(projectId,
				task.getName(),
				task.getDescription(),
				Priority.valueOf(task.getPriority()),
				Long.parseLong(task.getEstimatedDuration()),
				LocalDate.parse(task.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				LocalDate.parse(task.getEstimatedFinishDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Long.parseLong(task.getLeaderId()));
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
	public Project editProject(@PathVariable Long projectId, @RequestBody ProjectEditRequest project)
	{
		return projectService.editProject(projectId, project.getName(),project.getDescription(),Status.valueOf(project.getStatus()),LocalDate.parse(project.getEstimatedFinishDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),Long.parseLong(project.getLeaderId()));
	}


	@DeleteMapping("/projects/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {
		projectService.deleteById(projectId);
	}


	@GetMapping("/projects/{projectId}/tasks")
	public Collection<Task> getTasksForProject(@PathVariable Long projectId) {
		return taskService.getTasksByProject(projectId);
	}

	@GetMapping("/projects/{projectId}/tasks/{taskId}")
	public Optional<Task> getTask(@PathVariable Long projectId, @PathVariable Long taskId ) {
		return taskService.getTask(taskId);
	}

	@PutMapping("/projects/{projectId}/tasks/{taskId}")
	public Task editTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskEditRequest task) {
		return taskService.editTask(projectId,
				taskId,
				task.getName(),
				task.getDescription(),
				Priority.valueOf(task.getPriority()),
				Status.valueOf(task.getStatus()),
				Long.parseLong(task.getEstimatedDuration()),
				LocalDate.parse(task.getEstimatedFinishDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				Long.parseLong(task.getLeaderId()));
	}

	@DeleteMapping("/projects/{projectId}/tasks/{taskId}")
	public void deleteTask(@PathVariable Long taskId) {
		taskService.deleteById(taskId);
	}

	public Ticket getTicket(Long ticketId)
	{
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		String url = "https://psa-soporte-1yfx.onrender.com/tickets/" + ticketId;
		ResponseEntity<TicketRequest> responseEntity = restTemplate.getForEntity(
				url,
				TicketRequest.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			TicketRequest ticket = responseEntity.getBody();

			if (ticket != null) {
				return new Ticket(ticket.getCode(), ticket.getTitle(), ticket.getStatus(),ticket.getSeverity());
			}
		}
		return null;
	}
	@GetMapping("/projects/{projectId}/tasks/{taskId}/tickets")
	public Collection<Ticket> getTicketsForTask( @PathVariable Long taskId) {
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		String url = "https://psa-soporte-1yfx.onrender.com/tickets/associatedTask?taskId=" + taskId;
		ResponseEntity<Long[]> responseEntity = restTemplate.getForEntity(
				url,
				Long[].class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Long[] ticketIds = responseEntity.getBody();

			if (ticketIds != null) {
				List<Ticket> tickets  = new ArrayList<Ticket>();
				for (Long ticketId:ticketIds)
				{
					Ticket ticket = getTicket(ticketId);
					if (ticket != null)
					{
						tickets.add(ticket);
					}
				}
				return tickets;
			}
		}

		return Collections.emptyList();
	}
	@GetMapping("/employees")
	public Collection<Employee> getEmployees() {
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(
				"https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos",
				Employee[].class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Employee[] employees = responseEntity.getBody();

			if (employees != null) {
				return Arrays.asList(employees);
			}
		}

		return Collections.emptyList();
	}
	@GetMapping("/employees/{employeeId}")
	public  ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId)
	{
		Optional<Employee> employeeOptional = getEmployees().stream().filter(employee -> employee.getIdNumber().equals(employeeId)).findFirst();
		return ResponseEntity.of(employeeOptional);

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
