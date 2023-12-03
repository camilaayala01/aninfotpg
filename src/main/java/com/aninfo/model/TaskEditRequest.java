package com.aninfo.model;

public class TaskEditRequest {
    String name;
    String description;
    String priority;
    String status;
    String estimatedDuration;
    String estimatedFinishDate;
    String leaderId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEstimatedDuration() {
        return estimatedDuration;
    }

    public String getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    public String getPriority()
    {
        return priority;
    }
    public String getStatus(){return status;}

    public String getLeaderId() {return leaderId;}

    public TaskEditRequest() {

    }
}
