package com.aninfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketRequest {
    @JsonProperty("code")

    private Long code;

    @JsonProperty("title")
    private  String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private  Status status;
    @JsonProperty("severity")
    private  Severity severity;
    @JsonProperty(" priority")
    private  Priority priority;
    @JsonProperty("product")
    private String product;
    @JsonProperty("version")
    private String version;
    @JsonProperty("employeeId")
    private  Long employeeId;
    @JsonProperty("clientId")
    private  Long clientId;
    @JsonProperty("associatedTasks")
    private  List<Long> associatedTasks;
    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("closingDate")
    private  LocalDate closingDate;



    public Long getCode(){ return code;}
    public String getTitle(){ return title;}
    public String getDescription(){ return description;}
    public Status getStatus(){ return status;}
    public Severity getSeverity(){ return severity;}
    public Priority getPriority(){ return priority;}
    public String getProduct(){ return product;}
    public String getVersion(){ return version;}
    public Long getAssignatedEmployeeId(){ return employeeId;}
    public Long getClientId(){ return clientId;}
    public List<Long> getAssociatedTasks(){ return associatedTasks;}
    public LocalDate getStartDate(){ return startDate;}
    public LocalDate getClosingDate(){ return closingDate;}

}