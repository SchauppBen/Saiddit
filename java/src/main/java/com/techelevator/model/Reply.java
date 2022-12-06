package com.techelevator.model;

import java.security.Timestamp;

public class Reply {

    private int replyId;
    private User userFrom;
    private User userTo;
    private int postId;
    private String replyText;
    private String mediaLink;
    private Timestamp dateTime;

    public Reply(int replyId, User userFrom, User userTo, int postId, String replyText, String mediaLink, Timestamp dateTime) {
        this.replyId = replyId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.postId = postId;
        this.replyText = replyText;
        this.mediaLink = mediaLink;
        this.dateTime = dateTime;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
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

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

}
