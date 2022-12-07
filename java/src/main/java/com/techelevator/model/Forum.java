package com.techelevator.model;

import java.sql.Timestamp;

public class Forum {

    private int forumId;
    private String name;
    private String description;
    private Timestamp dateCreated;

    public Forum(int forumId, String name, String description, Timestamp dateCreated) {
        this.forumId = forumId;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
