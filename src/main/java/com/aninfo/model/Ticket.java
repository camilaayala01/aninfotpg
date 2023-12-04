package com.aninfo.model;
public class Ticket {

    private Long code;
    private String title;
    private String description;
    private Status status;
    private Severity severity;
    public Ticket() {
    }

    public Ticket(
            Long code,
            String title,
            Status status,
            Severity severity
    ) {
        this.code = code;
        this.title = title;
        this.status = status;
        this.severity = severity;
    }

    public String getTitle(){ return title;}

    public Long getCode() {return code;}
    public String getDescription(){ return description;}
    public Status getStatus(){ return status;}


}