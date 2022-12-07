package com.techelevator.controller;

import com.techelevator.dao.PostVoteDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PostVoteController {

    PostVoteDao postVoteDao;

    public PostVoteController(PostVoteDao postVoteDao) {
        this.postVoteDao = postVoteDao;
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}/upvote")
    public void upVotePost(@PathVariable int userId, @PathVariable int postId) {
        postVoteDao.upvotePost(userId, postId);
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}/downvote")
    public void downVotePost(@PathVariable int userId, @PathVariable int postId) {
        postVoteDao.downvotePost(userId, postId);
    }

}
