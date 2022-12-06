package com.techelevator.model;

import java.security.Timestamp;
import java.time.LocalDate;

public class Reply {

    private int replyId;
    private int userFrom;
    private int replyTo;
    private int postId;
    private String replyText;
    private String mediaLink;
    private LocalDate dateTime;

    public Reply(int replyId, int userFrom, int replyTo, int postId, String replyText, String mediaLink, LocalDate dateTime) {
        this.replyId = replyId;
        this.userFrom = userFrom;
        this.replyTo = replyTo;
        this.postId = postId;
        this.replyText = replyText;
        this.mediaLink = mediaLink;
        this.dateTime = dateTime;
    }

    public Reply() {}

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(int userFrom) {
        this.userFrom = userFrom;
    }

    public int getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(int replyTo) {
        this.replyTo = replyTo;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
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
