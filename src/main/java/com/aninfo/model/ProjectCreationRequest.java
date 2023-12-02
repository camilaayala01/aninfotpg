package com.aninfo.model;

public class ProjectCreationRequest {
    private String name;
    private String description;
    private String startDate;
    private String estimatedFinishDate;
    private String leaderId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public ProjectCreationRequest() {
    }
}
