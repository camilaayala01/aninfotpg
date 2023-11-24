package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Status status;
    private LocalDate startDate;
    private LocalDate estimatedFinishDate;
    private Long projectLeaderId;
    // project leader, when we know what it is

    public String getName(){ return name; }
    public Long getId(){ return id; }
    public String getDescription(){ return description; }
    public Status getStatus(){ return status; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getEstimatedFinishDate(){ return estimatedFinishDate; }

    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
    public void setStatus(Status status){ this.status = status; }
    public void setEstimatedFinishDate(LocalDate estimatedFinishDate){ this.estimatedFinishDate = estimatedFinishDate; }

    public Project(String name, String description, LocalDate startDate, LocalDate estimatedFinishDate, Long projectLeaderId){
        this.name = name;
        this.status = Status.NOT_STARTED;
        this.description = description;
        this.startDate = startDate;
        this.estimatedFinishDate = estimatedFinishDate;
        this.projectLeaderId = projectLeaderId;
    }

    public void editProject(String name, String description, Status status, LocalDate estimatedFinishDate)
    {
        setName(name);
        setDescription(description);
        setStatus(status);
        setEstimatedFinishDate(estimatedFinishDate);
    }

    public Project() {

    }
}
