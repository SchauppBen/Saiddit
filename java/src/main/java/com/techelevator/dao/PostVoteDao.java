package com.techelevator.dao;

import com.techelevator.model.PostVote;

public interface PostVoteDao {

    void upVotePost(PostVote postVote);

    void downVotePost(PostVote postVote);
}
