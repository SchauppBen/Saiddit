package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.model.Forum;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ForumController {

    private final ForumDao forumDao;

    public ForumController(ForumDao forumDao) {
        this.forumDao = forumDao;
    }

    @PostMapping(path = "/forums/createNewForum")
    public Forum createNewForum(@Valid @RequestBody Forum forum) {
        return forumDao.createNewForum(forum);
    }

    @GetMapping(path = "/forums/{forumName}")
    public Forum getForumByName(@PathVariable String forumName) {
        return forumDao.getForumByName(forumName);
    }

}
