package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Project {

    @Id
    private String projectCode;

    private ProjectStatus status;
    private String clientId;

    // private ArrayList<Ticket> tickets;
    private ArrayList<Task> tasks;

    Project(String product, String version, String clientId) {
        this.projectCode = product + version;
        this.status = ProjectStatus.NOTSTARTED;
        this.clientId = clientId;

        // this.tickets = new ArrayList<Ticket>();
        this.tasks = new ArrayList<Task>();
    }

    public Project() {

    }
}
