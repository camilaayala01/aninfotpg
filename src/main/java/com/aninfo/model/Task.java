package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numTask;

    private String projectId;
    private String description;
    private ProjectStatus status;
    private Priority priority;
    private Long storyPoints;
    private Long duration;

    private ArrayList<UserStory> userStories;

    public Task(String projectId, String description, Long storyPoints, Priority priority, Long estimatedDuration) {
        this.projectId = projectId;
        this.description = description;
        this.status = ProjectStatus.NOTSTARTED;
        this.priority = priority;
        this.storyPoints = storyPoints;
        this.duration = estimatedDuration;
        this.userStories = new ArrayList<UserStory>();
    }

    public Task() {

    }
}
