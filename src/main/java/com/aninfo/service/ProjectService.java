package com.aninfo.service;

import com.aninfo.model.Priority;
import com.aninfo.model.Project;
import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.model.Task;
import com.aninfo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

    public Project createProject(String product, String version, String clientId) {

    }

    public Collection<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project findById(String id) {
        return projectRepository.findById(id).orElseThrow(() -> new InvalidProjectException("No project found with that CBU"));
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }


    private Task createTask(String projectId, String name,String description,Long storyPoints, Priority priority,Long estimatedDuration)
    {
        Project project = findById(projectId);
        Task task = new Task(projectId,description,storyPoints,priority,estimatedDuration);
        task = taskService.createTask(task);
        return task;
    }

    public Collection<Task> getTasks() {
        return taskService.getTasks();
    }

    public Collection<Task> getTaskByProject() {

    }

    public void deleteTask(Long id) {

    }

    public Optional<Task> getTask(Long id) {
        return taskService.findById(id);
    }


}
