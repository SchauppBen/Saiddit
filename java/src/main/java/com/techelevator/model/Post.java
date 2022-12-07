package com.techelevator.model;

import java.time.LocalDate;

public class Post {

    private int postId;
    private int userId;
    private int forumId;
    private String forumName;
    private String title;
    private String text;
    private String mediaLink;
    private LocalDate dateTime;

    public Post(int postId, int userId, int forumId, String title, String text, String mediaLink) {
        this.postId = postId;
        this.userId = userId;
        this.forumId = forumId;
        this.title = title;
        this.text = text;
        this.mediaLink = mediaLink;
    }

    public Post() {}

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
