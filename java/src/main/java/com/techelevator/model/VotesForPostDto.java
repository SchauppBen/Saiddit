package com.techelevator.model;

public class VotesForPostDto {

    private int postId;
    private int upvotes;
    private int downvotes;

    public VotesForPostDto(int postId, int upvotes, int downvotes) {
        this.postId = postId;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public VotesForPostDto() {}

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
