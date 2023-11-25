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
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Long projectLeaderId;

    public String getName(){ return name; }
    public Long getId(){ return id; }
    public String getDescription(){ return description; }
    public Status getStatus(){ return status; }
    public LocalDate getCreationDate(){ return creationDate; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getEstimatedFinishDate(){ return finishDate; }

    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
    public void setStatus(Status status){ this.status = status; }
    public void setFinishDate(LocalDate finishDate){ this.finishDate = finishDate; }

    public Project(String name, String description, LocalDate startDate, LocalDate finishDate, Long projectLeaderId){
        this.name = name;
        this.status = Status.NOT_STARTED;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.projectLeaderId = projectLeaderId;
    }

    public void editProject(String name, String description, Status status, LocalDate finishDate)
    {
        //and employee?
        setName(name);
        setDescription(description);
        setStatus(status);
        setFinishDate(finishDate);
    }

    public Project() {

    }
}
