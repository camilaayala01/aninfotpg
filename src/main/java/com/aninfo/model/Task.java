package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long projectId;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Long estimatedDuration;
    private LocalDate startDate;
    private LocalDate finishDate;
    //  comentarios y responsable

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public Long getProjectId() { return projectId;}
    public String getDescription(){ return description; }
    public Status getStatus(){ return status; }
    public Priority getPriority(){ return priority; }
    public Long getEstimatedDuration(){ return estimatedDuration; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getFinishDate(){ return finishDate; }

    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
    public void setStatus(Status status){ this.status = status; }
    public void setPriority(Priority priority){ this.priority = priority; }
    public void setEstimatedDuration(Long estimatedDuration){ this.estimatedDuration = estimatedDuration; }
    public void setFinishDate(LocalDate finishDate){ this.finishDate = finishDate; }

    public Task() {

    }

    public Task(Long projectId, String name, String description, Priority priority, Long estimatedDuration, LocalDate startDate, LocalDate finishDate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.status = Status.NOT_STARTED;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public void editTask(String name, String description, Priority priority, Status status, Long estimatedDuration, LocalDate finishDate)
    {
        setName(name);
        setDescription(description);
        setPriority(priority);
        setStatus(status);
        setEstimatedDuration(estimatedDuration);
        setFinishDate(finishDate);
    }

}
