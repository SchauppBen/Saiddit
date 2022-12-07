package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private final PostDao postDao;

    public PostController(PostDao postDao) {
        this.postDao = postDao;
    }

    @GetMapping(path = "/posts")
    public List<Post> getPostsForHomePage() {
        return postDao.getPostsForHomePage();
    }

    @PostMapping(path = "/{forumName}/posts/createNewPost")
    public Post createNewPost(@RequestBody Post newPost) {
        return postDao.createNewPost(newPost);
    }

    @GetMapping(path = "/{forumName}/posts")
    public List<Post> getPostsByForum(@PathVariable String forumName) {
        return postDao.getPostsByForum(forumName);
    }

}
