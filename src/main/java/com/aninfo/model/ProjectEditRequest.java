package com.aninfo.model;

public class ProjectEditRequest {

    String name;
    String description;
    String status;
    String estimatedFinishDate;
    String leaderId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    public String getEstimatedFinishDate() {
        return estimatedFinishDate;
    }


    public String getStatus(){return status;}

    public String getLeaderId() {return leaderId;}

    public ProjectEditRequest() {
    }
}
