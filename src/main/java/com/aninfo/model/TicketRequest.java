package com.aninfo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketRequest {

    private Long code;

    private final String title;
    private final String description;
    private final Status status;
    private final Severity severity;
    private final Priority priority;
    private final String product;
    private final String version;
    private final Long employeeId;
    private final Long clientId;

    private final List<Long> associatedTasks;
    private final LocalDate startDate;
    private final LocalDate closingDate;



    public TicketRequest(
            String title,
            String description,
            Status status,
            Severity severity,
            Priority priority,
            String product,
            String version,
            Long clientId,
            Long employeeId,
            LocalDate closingDate
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.severity = severity;
        this.priority = priority;
        this.product = product;
        this.version = version;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.associatedTasks = new ArrayList<>();
        this.startDate = LocalDate.now();
        this.closingDate = closingDate;
    }

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