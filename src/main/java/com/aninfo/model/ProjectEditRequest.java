package com.aninfo.model;

public class ProjectEditRequest {

    String name;
    String description;
    String priority;
    String status;
    String estimatedDuration;
    String finishDate;

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
        return finishDate;
    }

    public String getPriority()
    {
        return priority;
    }
    public String getStatus(){return status;}
    public String getFinishDate(){return  finishDate;}

    //public String getLeaderId() {return leaderId;}
}
