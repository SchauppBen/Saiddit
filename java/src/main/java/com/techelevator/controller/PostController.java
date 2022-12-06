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
    private final PostDao dao;

    public PostController(PostDao dao) {
        this.dao = dao;
    }

    @GetMapping(path = "/")
    public List<Post> getPostsForHomePage() {
        return dao.getPostsForHomePage();
    }

    @GetMapping(path = "/{forumName}")
    public List<Post> getPostsByForum(@PathVariable String forumName) {
        return dao.getPostsByForum(forumName);
    }



}
