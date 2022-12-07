package com.techelevator.dao;

import com.techelevator.model.PostVote;

public interface PostVoteDao {

    void upvotePost(int userId, int postId);

    void downvotePost(int userId, int postId);
}
