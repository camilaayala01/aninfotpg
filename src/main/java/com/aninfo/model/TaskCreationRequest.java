package com.aninfo.model;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public class TaskCreationRequest {
    private String name;
    private String description;
    private String priority;
    private String estimatedDuration;

    private String startDate;
    private String estimatedFinishDate;
   // private String responsibleId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority()
    {
        return priority;
    }
    public String getEstimatedDuration(){return estimatedDuration;}

    public String getStartDate() {
        return startDate;
    }

    public String getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    //public String getresponsableId() {return responsableId;}

}
