package com.techelevator.dao;

import com.techelevator.model.PostVote;

public interface PostVoteDao {

    void upvotePost(PostVote postVote);

    void downvotePost(PostVote postVote);
}
