package com.aninfo.integration.cucumber;

import com.aninfo.Memo1TPG;
import com.aninfo.model.Priority;
import com.aninfo.model.Status;
import com.aninfo.model.Task;
import com.aninfo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@ContextConfiguration(classes = Memo1TPG.class)
@WebAppConfiguration
public class TaskIntegrationServiceTest {

    @Autowired
    TaskService taskService;

    public Task createTask(String name) {
        return taskService.createTask(1L,name,"", Priority.HIGH, 200L, LocalDate.now(),LocalDate.now(),1L);
    }
    public Task createTask(String name, LocalDate estimatedfinishedDate){
        return taskService.createTask(1L,name,"", Priority.HIGH, 200L, LocalDate.now(),estimatedfinishedDate,1L);
    }

    public Task createTask(String name, String descripcion) {
        return taskService.createTask(1L,name,descripcion, Priority.HIGH, 200L, LocalDate.now(),LocalDate.now(),1L);
    }
    Task createTask() {
        return taskService.createTask(1L,"","", Priority.HIGH, 200L, LocalDate.now(),LocalDate.now(),1L);
    }
    public Collection<Task> findAllTaskInProject(Long id){return taskService.getTasksByProject(id);}
    public Optional<Task> findById(Long taskId) {
        return taskService.getTask(taskId);
    }

    public Task findByName(String name) {
        return taskService.findByName(name);
    }

    public Collection<Task> getAllTask(){
        return taskService.getTasks();
    }

    public void deleteAll(){ taskService.deleteAll();}

    Task editTask(Long id, String name) {
        return taskService.editTask(1L,id,name, "ejemplo descripcion", Priority.HIGH, Status.IN_PROGRESS, 200L, LocalDate.now(), 1L);

    }
    Task editTask(Long id, String description, String name) {
        return taskService.editTask(1L,id,name, description, Priority.HIGH, Status.IN_PROGRESS, 200L, LocalDate.now(), 1L);
    }
    Task editTask(Long id, Status status, String name)
    {
        return taskService.editTask(1L,id,name, "ejemplo descripcion", Priority.HIGH, status, 200L, LocalDate.now(), 1L);
    }
    Task editTask(Long id, LocalDate estimatedFinishDate, String name) {
        return taskService.editTask(1L,id,name, "ejemplo descripcion", Priority.HIGH, Status.IN_PROGRESS, 200L, estimatedFinishDate, 1L);
    }
    public void deleteById(Long id)
    {
        taskService.deleteById(id);
    }
}
