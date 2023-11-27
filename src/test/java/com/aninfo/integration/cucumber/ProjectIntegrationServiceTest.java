package com.aninfo.integration.cucumber;

import com.aninfo.Memo1TPG;
import com.aninfo.model.Project;
import com.aninfo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Collection;

@ContextConfiguration(classes = Memo1TPG.class)
@WebAppConfiguration
public class ProjectIntegrationServiceTest {

    @Autowired
    ProjectService projectService;

    Project createProject(String name) {
        return projectService.createProject(name, "", LocalDate.now(), LocalDate.now(), 1L);
    }
    Project createProject() {
        return projectService.createProject("", "", LocalDate.now(), LocalDate.now(), 1L);
    }

    public Project findById(Long projectId) {
        return projectService.findById(projectId);
    }
    public Project findByName(String name) {
        return projectService.findByName(name);
    }

    public Collection<Project> findAllProjects(){return projectService.findAll();}

    public void deleteAll(){ projectService.deleteAll();}
}


