package com.techelevator.controller;

import com.techelevator.dao.PostVoteDao;
import com.techelevator.model.PostVote;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        postVoteDao.upVotePost(postVote);
    }

    @PostMapping(path = "/posts/downvote")
    @ResponseStatus(HttpStatus.CREATED)
    public void downVotePost(@RequestBody PostVote postVote) {
        postVoteDao.downVotePost(postVote);
    }

    @GetMapping(path = "/posts/votes")
    public List<PostVote> getAllPostVotes() {
        return postVoteDao.getAllPostVotes();
    }
    @GetMapping(path = "/posts/votes/{userId}")
    public List<PostVote> getPostVotesByUser(@PathVariable int userId) {
        return postVoteDao.getPostVotesByUser(userId);
    }

    @GetMapping(path = "/posts/{postId}/votes/")
    public List<PostVote> getPostVotesByPost(@PathVariable int postId) {
        return postVoteDao.getPostVotesByPost(postId);
    }

    @GetMapping(path = "/posts/votes/{userId}/up")
    public List<PostVote> getUpVotesByUser(@PathVariable int userId) {
        return postVoteDao.getUpVotesByUser(userId);
    }

    @GetMapping(path = "/posts/votes/{userId}/down")
    public List<PostVote> getDownVotesByUser(@PathVariable int userId) {
        return postVoteDao.getDownVotesByUser(userId);
    }

    @GetMapping(path = "/posts/{postId}/votes/up")
    public List<PostVote> getUpVotesByPost(@PathVariable int postId) {
        return postVoteDao.getUpVotesByPost(postId);
    }

    @GetMapping(path = "/posts/{postId}/votes/down")
    public List<PostVote> getDownVotesByPost(@PathVariable int postId) {
        return postVoteDao.getDownVotesByPost(postId);
    }

}
