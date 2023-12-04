package com.aninfo.service;

import com.aninfo.exceptions.InvalidTaskException;
import com.aninfo.exceptions.TaskNameAlreadyTaken;
import com.aninfo.model.Priority;
import com.aninfo.model.Status;
import com.aninfo.model.Task;
import com.aninfo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Long projectId, String name, String description, Priority priority, Long estimatedDuration, LocalDate startDate, LocalDate finishDate)
    {
        taskRepository.findTaskByName(name).ifPresent(x -> { if (x.getProjectId().equals(projectId)) {throw new TaskNameAlreadyTaken("Task name already exists in project");}});
        Task task = new Task(projectId, name, description, priority, estimatedDuration, startDate, finishDate);
        return taskRepository.save(task);
    }

    public void save(Task task)
    {
        taskRepository.save(task);
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Collection<Task> getTasksByProject(Long id) {
        return taskRepository.findTasksByProjectId(id);
    }

    private boolean nameTakenByOtherTask(String name, Long projectId, Long taskId) {

        Optional<Task> taskOptional = taskRepository.findTaskByName(name);
        if (taskOptional.isPresent()) {
            Task x = taskOptional.get();
            return !x.getId().equals(taskId) && x.getProjectId().equals(projectId);
        }

        return false;
    }
    public Task findByName(String name) {
        return taskRepository.findTaskByName(name).orElseThrow(() -> new InvalidTaskException("No task found with that name"));
    }

    public Task editTask(Long projectId, Long taskId, String name, String description, Priority priority, Status status, Long estimatedDuration, LocalDate finishDate, Long leaderId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new InvalidTaskException("Task not found"));
        if (nameTakenByOtherTask(name, projectId, taskId)){
            throw new TaskNameAlreadyTaken("Task name already exists in this project");
        }
        task.editTask(name, description, priority, status, estimatedDuration, finishDate, leaderId);
        return taskRepository.save(task);
    }

    public void deleteAll(){ taskRepository.deleteAll(); }
}
