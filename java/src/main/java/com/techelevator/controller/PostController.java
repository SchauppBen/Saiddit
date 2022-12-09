package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping(path = "forums/posts/")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@RequestBody Post newPost, Principal principal) {
        if (principal != null) {
            newPost.setUserId(userDao.findIdByUsername(principal.getName()));
            Post createdPost = postDao.createNewPost(newPost);
            if (createdPost == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
            return createdPost;
        } else {
            return null;
        }
    }

    @PutMapping(path = "/posts/{postId}")
    public void editPost(@PathVariable int postId, @RequestBody Post post, Principal principal) {
        if (principal != null && userDao.findIdByUsername(principal.getName()) == post.getUserId()) {
            postDao.editPost(postId, post);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
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
