package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private final PostDao postDao;
    private final UserDao userDao;

    public PostController(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping(path = "/posts")
    public List<Post> getPostsForHomePage() {
        return postDao.getPostsForHomePage();
    }

    @PostMapping(path = "forums/{forumName}/posts/")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@RequestBody Post newPost, Principal principal) {
        newPost.setUserId(userDao.findIdByUsername(principal.getName()));
        return postDao.createNewPost(newPost);
    }

    @PutMapping(path = "/posts/{postId}")
    public void editPost(@PathVariable int postId, @RequestBody Post post) {
        postDao.editPost(postId, post);
    }

    @GetMapping(path = "/posts/search/{searchString}")
    public List<Post> searchPosts(@PathVariable String searchString) {
        return postDao.searchPosts(searchString);
    }

    @GetMapping(path = "forums/{forumName}/posts")
    public List<Post> getPostsByForum(@PathVariable String forumName) {
        return postDao.getPostsByForum(forumName);
    }

}
