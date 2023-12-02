package com.aninfo.service;

import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Priority;
import com.aninfo.model.Project;
import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.model.Status;
import com.aninfo.model.Task;
import com.aninfo.repository.ProjectRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(String name, String description, LocalDate startDate, LocalDate estimatedFinishDate,Long projectLeaderId) {
        projectRepository.findProjectByName(name).ifPresent(x -> {throw new ProjectNameAlreadyTakenException("Name already taken");});
        Project project = new Project(name, description, startDate, estimatedFinishDate,projectLeaderId);
        return projectRepository.save(project);
    }

    public Collection<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project findByName(String name) {
        return projectRepository.findProjectByName(name).orElseThrow(() -> new InvalidProjectException("No project found with that name"));
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new InvalidProjectException("No project found"));
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public void deleteAll(){ projectRepository.deleteAll(); }

    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project editProject(Long id, String name, String description, Status status, LocalDate estimatedFinishDate, Long leaderId) {
        projectRepository.findProjectByName(name).ifPresent(x -> { if (!x.getId().equals(id)) {throw new ProjectNameAlreadyTakenException("Name already taken");}});
        Project project = projectRepository.findById(id).orElseThrow(() -> new InvalidProjectException("Project not found"));
        project.editProject(name, description, status, estimatedFinishDate, leaderId);
        return projectRepository.save(project);
    }
}
