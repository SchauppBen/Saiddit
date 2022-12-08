package com.techelevator.controller;

import com.techelevator.dao.PostVoteDao;
import com.techelevator.model.PostVote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PostVoteController {

    PostVoteDao postVoteDao;

    public PostVoteController(PostVoteDao postVoteDao) {
        this.postVoteDao = postVoteDao;
    }

    @PostMapping(path = "posts/upvote")
    @ResponseStatus(HttpStatus.CREATED)
    public void upVotePost(@RequestBody PostVote postVote) {
        postVoteDao.upvotePost(postVote);
    }

    @PostMapping(path = "/posts/downvote")
    @ResponseStatus(HttpStatus.CREATED)
    public void downVotePost(@RequestBody PostVote postVote) {
        postVoteDao.downvotePost(postVote);
    }

}
